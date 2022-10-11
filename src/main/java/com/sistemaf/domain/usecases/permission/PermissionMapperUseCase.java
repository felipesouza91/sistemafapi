package com.sistemaf.domain.usecases.permission;

import com.sistemaf.api.dto.input.PermissionsInput;
import com.sistemaf.api.dto.model.PermissionDto;
import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.model.Permissao;
import com.sistemaf.domain.repository.security.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PermissionMapperUseCase {


  @Autowired
  private ListPermissionByCategoryUseCase listPermissionByCategoryUseCase;

  @Autowired
  private PermissaoRepository permissaoRepository;

  public List<Permissao> toListModel(List<PermissionsInput> inputList) {
    List<Permissao> permissions = this.permissaoRepository.findAll();
    if(inputList == null) {
      throw new BusinessException("As Permissões são obrigatorias");
    }
    List<Permissao> newUserPermissions = new ArrayList<>();
    inputList.stream().forEach(item -> {
      if(item.getRead()) {
        newUserPermissions.add(this.searchPermission(permissions,"RL_PES_"+item.getNameId()));
      }
      if (item.getWrite()) {
        newUserPermissions.add(this.searchPermission(permissions,"RL_CAD_"+item.getNameId()));
      }
      if( item.getRemove()) {
        newUserPermissions.add(this.searchPermission(permissions,"RL_REM_"+item.getNameId()));
      }
    });
    return newUserPermissions;
  }

  public Set<PermissionDto> toListDto(List<Permissao> permissionList) {
    Set<PermissionDto> permattedList =  listPermissionByCategoryUseCase.execute().stream().map(
            item -> {
              PermissionDto permissionDto = item;

              if(permissionList.stream().anyMatch(permision -> permision.getDescricao().equals("RL_CAD_"+item.getNameId()))) {
                item.setWrite(true);
              }
              if(permissionList.stream().anyMatch(permision -> permision.getDescricao().equals("RL_PES_"+item.getNameId()))) {
                item.setRead(true);
              }
              if(permissionList.stream().anyMatch(permision -> permision.getDescricao().equals("RL_REM_"+item.getNameId()))) {
                item.setRemove(true);
              }
              return item;
            }
    ).collect(Collectors.toSet());
    return permattedList;
  }

  private Permissao searchPermission(List<Permissao> permissions, String roleName) {
    return  permissions.stream().filter( permision -> permision.getDescricao().equals(roleName))
            .findFirst().orElseThrow(() -> new BusinessException("Permisao não existe"));
  }

}
