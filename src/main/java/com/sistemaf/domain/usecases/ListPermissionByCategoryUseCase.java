package com.sistemaf.domain.usecases;

import com.sistemaf.api.dto.model.PermissionDto;
import com.sistemaf.domain.repository.security.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

public class ListPermissionByCategoryUseCase {

  @Autowired
  private PermissaoRepository repository;

  public Set<PermissionDto> execute() {
    Set<String> permissionsClasses = this.repository.findAll().stream().map(
        item -> item.getDescricao().substring(7)
    ).collect(Collectors.toSet());

    Set<PermissionDto> permissionsFormatted = permissionsClasses.stream().map(
        item -> PermissionDto.builder().decription(item).delete(false).read(false)
            .write(false).build()
    ).collect(Collectors.toSet());

    return  permissionsFormatted;
  }
}
