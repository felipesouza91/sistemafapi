package com.sistemaf.domain.usecases;

import com.sistemaf.api.dto.input.AccessGroupInputData;
import com.sistemaf.domain.model.GrupoAcesso;
import com.sistemaf.domain.model.Permissao;
import com.sistemaf.domain.repository.security.PermissaoRepository;
import com.sistemaf.domain.repository.security.grupoacesso.GrupoAcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaveAccessGroupUseCase {

  @Autowired
  private PermissaoRepository permissaoRepository;

  @Autowired
  private GrupoAcessoRepository grupoAcessoRepository;

  public GrupoAcesso execute(AccessGroupInputData data) {
    var permissions = this.permissaoRepository.findAll();
    List<Permissao> newUserPermissions = new ArrayList<>();
    data.getPermissions().stream().forEach(item -> {
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

    GrupoAcesso grupoAcesso = new GrupoAcesso();
    grupoAcesso.setPermissoes(newUserPermissions);
    grupoAcesso.setAtivo(data.getAtivo());
    grupoAcesso.setDescricao(data.getDescricao());

    return this.grupoAcessoRepository.save(grupoAcesso);
  }

  private Permissao searchPermission(List<Permissao> permissions, String roleName) {
   return  permissions.stream().filter( permision -> permision.getDescricao().equals(roleName)).findFirst().get();
  }

}
