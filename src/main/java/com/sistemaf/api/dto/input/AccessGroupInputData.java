package com.sistemaf.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema()
public class AccessGroupInputData {

  @NotNull
  @Schema(description =  "Permission status", example = "true")
  private Boolean ativo;

  @NotNull
  @Size(max=50)
  @Schema(description =  "Permission description", example = "Any description")
  private String descricao;

  @Valid
  @NotNull
  @Schema(description =  "Permission list")
  private List<PermissionsInput> permissions;

}
