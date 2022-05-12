package com.sistemaf.api.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {
	
	@NotBlank
	@NotNull
	@Size(min=4, max=70)
	@ApiModelProperty(value = "City name",example = "Uberlandia", required = true)
	private String nome;

}
