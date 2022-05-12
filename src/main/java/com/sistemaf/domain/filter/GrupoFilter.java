package com.sistemaf.domain.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("GroupFilter")
public class GrupoFilter {

	@ApiModelProperty(value = "Group name", example = "Company group")
	private String nome;

}
