package com.sistemaf.core.security.authorizationserver;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@Component
@Validated
@ConfigurationProperties("app.env.security")
public class AppSecurityProperties {

  @NotBlank
  private String providerUrl;

  @NotBlank
  private  String clientName;

  @NotBlank
  private String clientSecret;

  @NotBlank
  private Set<String> redirectUris;

  private boolean enableHttps;

  private int accessTokenValiditySeconds = 1800 ; // 30 minutos

  private int refreshTokenValiditySeconds = 3600*12; // 24 hours

}
