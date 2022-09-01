package com.sistemaf.core.openapi;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFoxConfig {


    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema Api")
                        .description("API for clients")
                        .version("0.0.8a")
                        .contact(new Contact()
                                        .name("Felipe Santana")
                                        .url( "https://fsantana.dev" )))

                ;
    }


}
