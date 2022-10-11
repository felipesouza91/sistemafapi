package com.sistemaf.domain.usecases.accessgroup;

import com.sistemaf.api.dto.model.AccessGroupDto;
import com.sistemaf.api.dto.model.PermissionDto;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.GrupoAcesso;
import com.sistemaf.domain.repository.security.grupoacesso.GrupoAcessoRepository;
import com.sistemaf.domain.usecases.permission.PermissionMapperUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LoadFormattedGroupAccessUseCase {

  @Autowired
  private GrupoAcessoRepository grupoAcessoRepository;

  @Autowired
  private PermissionMapperUseCase permissionMapperUseCase;

  public AccessGroupDto execute(Long groupAccessCode) {
    GrupoAcesso grupoAcesso = this.grupoAcessoRepository.findById(groupAccessCode).orElseThrow(()-> new EntityNotFoundException("O Grupo de acesso solicitado não existe"));
    Set<PermissionDto> all = this.permissionMapperUseCase.toListDto(grupoAcesso.getPermissoes());
    return AccessGroupDto.builder()
            .id(grupoAcesso.getId())
            .ativo(grupoAcesso.getAtivo())
            .descricao(grupoAcesso.getDescricao())
            .permissions(all)
            .build();
  }

}
