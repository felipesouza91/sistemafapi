package com.sistemaf.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Component
@Validated
@ConfigurationProperties("app.env")
public class SistemFApiProperty {
	
	private List<String> origemPermitida = new ArrayList<>();
	
	@NotBlank
	private String apiUrl;
	
	private String apiIntegrationUrl = null;


}
