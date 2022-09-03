package com.sistemaf.core.openapi;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(name = "security_auth", type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows( password = @OAuthFlow(
                tokenUrl = "oauth/token",
                scopes = {
                        @OAuthScope(name = "read", description = "read scope"),
                        @OAuthScope(name = "write", description = "write scope")
                }

            )
        )
)
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
