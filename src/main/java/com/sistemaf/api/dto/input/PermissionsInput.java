package com.sistemaf.api.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionsInput {

  @NotBlank
  private String nameId;
  @NotNull
  private Boolean read;
  @NotNull
  private Boolean write;
  @NotNull
  private Boolean remove;
}
