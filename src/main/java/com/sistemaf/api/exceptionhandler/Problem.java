package com.sistemaf.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problem")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {
	
	@ApiModelProperty(example = "400")
	private Integer status;
	
	@ApiModelProperty(example = "https://algaworksapi.com/recurso-nao-encontrado")
	private String type;
	
	@ApiModelProperty(example = "Resource not found")
	private String title;
	
	@ApiModelProperty(example = "There is no kitchen register with code XX")
	private String detail;
	
	@ApiModelProperty(example = "There is no kitchen register with code XX")
	private String userMessage;
	
	@ApiModelProperty(example = "2019-12-10T18:30:02.70444Z")
	private OffsetDateTime timestamp;
	
	@ApiModelProperty("List objects or fields with errors")
	private List<Field> objects;
	
	@ApiModel("ProblemObject")
	@Getter
	@Builder
	public static class Field {
		@ApiModelProperty(example = "Price")
		private String nome;
		@ApiModelProperty(example = "Price is required")
		private String userMessage;
	}

}
