package com.sistemaf.domain.usecases.accessgroup;

import com.sistemaf.api.dto.input.AccessGroupInputData;
import com.sistemaf.domain.model.GrupoAcesso;
import com.sistemaf.domain.model.Permissao;
import com.sistemaf.domain.repository.security.grupoacesso.GrupoAcessoRepository;
import com.sistemaf.domain.usecases.permission.PermissionMapperUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveAccessGroupUseCase {

  @Autowired
  private PermissionMapperUseCase permissionMapperUseCase;

  @Autowired
  private GrupoAcessoRepository grupoAcessoRepository;

  public GrupoAcesso execute(AccessGroupInputData data) {

    List<Permissao> newUserPermissions = permissionMapperUseCase.toListModel(data.getPermissions());
    GrupoAcesso grupoAcesso = new GrupoAcesso();
    grupoAcesso.setPermissoes(newUserPermissions);
    grupoAcesso.setAtivo(data.getAtivo());
    grupoAcesso.setDescricao(data.getDescricao());
    return this.grupoAcessoRepository.save(grupoAcesso);
  }



}
