package com.sistemaf.api.dto.model;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionDto {
  private String nameId;
  private String formattedName;
  private Boolean read;
  private Boolean write;
  private Boolean remove;
}
