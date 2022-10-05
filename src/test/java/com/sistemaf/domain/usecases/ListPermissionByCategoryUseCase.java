package com.sistemaf.domain.usecases;

import com.sistemaf.domain.repository.security.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

public class ListPermissionByCategoryUseCase {

  @Autowired
  private PermissaoRepository repository;

  public void execute() {
    Set<String> main = this.repository.findAll().stream().map(
        item -> item.getDescricao().substring(
            item.getDescricao().lastIndexOf('_')+1)
    ).collect(Collectors.toSet());

  }
}
