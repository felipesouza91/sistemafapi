package com.sistemaf.domain.usecases;

import com.sistemaf.api.dto.model.PermissionDto;
import com.sistemaf.domain.repository.security.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ListPermissionByCategoryUseCase {

  @Autowired
  private PermissaoRepository repository;

  public Set<PermissionDto> execute() {
    Set<String> permissionsClasses = this.repository.findAll().stream().map(
        item -> item.getDescricao().substring(7)
    ).collect(Collectors.toSet());

    Set<PermissionDto> permissionsFormatted = permissionsClasses.stream().map(
        item -> PermissionDto.builder().nameId(item)
            .formattedName(StringUtils.capitalize(
                item.toLowerCase().replaceAll("_", " ")))
            .remove(false).read(false)
            .write(false).build()
    ).collect(Collectors.toSet());
    return  permissionsFormatted;
  }
}
