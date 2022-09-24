package com.sistemaf.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
@Validated
@ConfigurationProperties("app.env")
public class SistemFApiProperty {

	@NotNull
	private List<String> allowOrigins;
	
	@NotBlank
	private String apiUrl;
	
	private String apiIntegrationUrl = "";


}
