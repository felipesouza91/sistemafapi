package com.sistemaf.domain.usecases.accessgroup;

import com.sistemaf.api.dto.input.AccessGroupInputData;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.GrupoAcesso;
import com.sistemaf.domain.model.Permissao;
import com.sistemaf.domain.repository.security.grupoacesso.GrupoAcessoRepository;
import com.sistemaf.domain.usecases.permission.PermissionMapperUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateAccessGroupUseCase {
  @Autowired
  private GrupoAcessoRepository repository;
  @Autowired
  private PermissionMapperUseCase permissionMapperUseCase;

  public GrupoAcesso execute(Long id, AccessGroupInputData data) {
    GrupoAcesso grupoAcesso = this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Grupo não encontrado"));
    List<Permissao> permissions = this.permissionMapperUseCase.toListModel(data.getPermissions());
    grupoAcesso.setAtivo(data.getAtivo());
    grupoAcesso.setPermissoes(permissions);
    grupoAcesso.setDescricao(data.getDescricao());
    return this.repository.save(grupoAcesso);
  }

}
