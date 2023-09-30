package com.sistemaf.infrastructure.util;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomizeProvide {

	@Autowired
	private Environment env;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	@Bean
	protected Module module() {
	    return new Hibernate5Module();
	}

}
