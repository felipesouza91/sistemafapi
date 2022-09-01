package com.sistemaf.domain.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "GroupFilter")
public class GrupoFilter {

	@Schema(description =  "Group name", example = "Company group")
	private String nome;

}
