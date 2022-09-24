package com.sistemaf.core.security.authorizationserver;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Component
@Validated
@ConfigurationProperties("app.security")
public class AppSecurityProperties {

  @NotBlank
  private String providerUrl;

  @NotBlank
  private  String clientName;

  @NotBlank
  private String clientSecret;

  @NotNull
  private Set<String> redirectUris;

  private boolean enableHttps = false;

  private int accessTokenValiditySeconds = 1800 ; // 30 minutos

  private int refreshTokenValiditySeconds = 3600*12; // 24 hours

}
