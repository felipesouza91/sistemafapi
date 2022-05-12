package com.sistemaf.infrastructure.util;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.sistemaf.infrastructure.util.integration.ApiConnection;
import com.smattme.MysqlExportService;

@Component
public class CustomizeProvide {

	@Autowired
	private Environment env;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Bean
	public ApiConnection getApiConnection() {
		return new ApiConnection();
	}
	
	@Bean
	protected Module module() {
	    return new Hibernate5Module();
	}

	@Bean
	public MysqlExportService mysqlExportService() {
		return new MysqlExportService(this.getMysqlProperties());
	}
	
	private Properties getMysqlProperties() {
		Properties properties = new Properties();
		properties.setProperty(MysqlExportService.DB_NAME, this.getDataBaseName());
		properties.setProperty(MysqlExportService.DB_USERNAME, env.getProperty("spring.datasource.username"));
		properties.setProperty(MysqlExportService.DB_PASSWORD, env.getProperty("spring.datasource.password"));
		properties.setProperty(MysqlExportService.PRESERVE_GENERATED_ZIP, "true");
		return properties;
	}
	
	private String getDataBaseName() {
		String url = env.getProperty("spring.datasource.url");

		int indexOfbase = url.lastIndexOf("/");
		int lastIndex = url.indexOf("?");
		if(lastIndex == -1 ) {
			 lastIndex = url.length();
		}
		String nome = url.substring(indexOfbase+1, lastIndex); 
		return nome;
	}
}
