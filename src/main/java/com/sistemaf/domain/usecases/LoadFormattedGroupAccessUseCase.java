package com.sistemaf.domain.usecases;

import com.sistemaf.api.dto.model.AccessGroupDto;
import com.sistemaf.api.dto.model.PermissionDto;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.GrupoAcesso;
import com.sistemaf.domain.repository.security.grupoacesso.GrupoAcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoadFormattedGroupAccessUseCase {

  @Autowired
  private GrupoAcessoRepository grupoAcessoRepository;

  @Autowired
  private ListPermissionByCategoryUseCase listPermissionByCategoryUseCase;

  public AccessGroupDto execute(Long groupAccessCode) {
    GrupoAcesso grupoAcesso = this.grupoAcessoRepository.findById(groupAccessCode).orElseThrow(()-> new EntityNotFoundException("O Grupo de acesso solicitado não existe"));
    Set<PermissionDto> all =  listPermissionByCategoryUseCase.execute().stream().map(
            item -> {
              PermissionDto permissionDto = item;

              if(grupoAcesso.getPermissoes().stream().anyMatch(permision -> permision.getDescricao().equals("RL_CAD_"+item.getNameId()))) {
                item.setWrite(true);
              }
              if(grupoAcesso.getPermissoes().stream().anyMatch(permision -> permision.getDescricao().equals("RL_PES_"+item.getNameId()))) {
                item.setRead(true);
              }
              if(grupoAcesso.getPermissoes().stream().anyMatch(permision -> permision.getDescricao().equals("RL_REM_"+item.getNameId()))) {
                item.setRemove(true);
              }
              return item;
            }
    ).collect(Collectors.toSet());
    return AccessGroupDto.builder()
            .id(grupoAcesso.getId())
            .ativo(grupoAcesso.getAtivo())
            .descricao(grupoAcesso.getDescricao())
            .permissions(all)
            .build();
  }

}
