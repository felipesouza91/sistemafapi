package com.sistemaf.api.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccessGroupInputData {

  @NotNull
  private Boolean ativo;

  @NotBlank
  private String descricao;

  @Valid
  private List<PermissionsInput> permissions;

}
