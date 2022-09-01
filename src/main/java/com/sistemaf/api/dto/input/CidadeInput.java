package com.sistemaf.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Schema(name="City Input")
public class CidadeInput {
	
	@NotBlank
	@NotNull
	@Size(min=4, max=70)
	@Schema(description = "City name",example = "Uberlandia", required = true)
	private String nome;

}
