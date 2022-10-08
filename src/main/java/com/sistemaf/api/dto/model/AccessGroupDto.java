package com.sistemaf.api.dto.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AccessGroupDto {

  private Long id;

  private Boolean ativo;

  private String descricao;

  private Set<PermissionDto> permissions;

}
