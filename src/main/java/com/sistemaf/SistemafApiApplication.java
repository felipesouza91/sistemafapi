package com.sistemaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.sistemaf.core.SistemFApiProperty;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(SistemFApiProperty.class)
@EnableCaching
public class SistemafApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemafApiApplication.class, args);
	}
	
}