package com.sistemaf.api.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@Schema(name = "Access Group Model")
public class AccessGroupDto {

  @Schema(description = "Access group code", example = "1")
  private Long id;

  @Schema(description = "Permission status", example = "true")
  private Boolean ativo;

  @Schema(description = "Permission description", example = "Any description")
  private String descricao;

  @Schema(description = "Permission list")
  private Set<PermissionDto> permissions;

}
