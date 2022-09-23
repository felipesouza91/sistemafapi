package com.sistemaf;

import com.sistemaf.core.io.Base64ProtocolResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.sistemaf.core.SistemFApiProperty;

import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(SistemFApiProperty.class)
@EnableCaching
public class SistemafApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication application = new SpringApplication(SistemafApiApplication.class);
		application.addListeners(new Base64ProtocolResolver());
		application.run(args);
	}
	
}