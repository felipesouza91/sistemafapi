package com.sistemaf.core.security.authorizationserver;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.core.io.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Validated
@Component
@ConfigurationProperties("app.security.jwt.keystore")
public class JwtKeyStoreProperties {

  @NotNull
  private Resource jksLocation;

  @NotBlank
  private String password;

  @NotBlank
  private String keypairAlias;
}
