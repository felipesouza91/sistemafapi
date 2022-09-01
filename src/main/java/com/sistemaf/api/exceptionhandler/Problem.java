package com.sistemaf.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Tag(name="Problem")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {
	
	@Schema(description = "400")
	private Integer status;
	
	@Schema(description = "https://algaworksapi.com/recurso-nao-encontrado")
	private String type;
	
	@Schema(description = "Resource not found")
	private String title;
	
	@Schema(description = "There is no kitchen register with code XX")
	private String detail;
	
	@Schema(description = "There is no kitchen register with code XX")
	private String userMessage;
	
	@Schema(description = "2019-12-10T18:30:02.70444Z")
	private OffsetDateTime timestamp;
	
	@Schema(description = "List objects or fields with errors")
	private List<Field> objects;
	
	@Tag(name = "ProblemObject")
	@Getter
	@Builder
	public static class Field {
		@Schema(description = "Price")
		private String nome;
		@Schema(description = "Price is required")
		private String userMessage;
	}

}
