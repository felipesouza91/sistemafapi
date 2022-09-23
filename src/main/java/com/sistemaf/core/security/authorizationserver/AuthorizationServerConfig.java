package com.sistemaf.core.security.authorizationserver;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.security.KeyStore;
import java.time.Duration;
import java.util.Arrays;

@Configuration
public class AuthorizationServerConfig {

  @Autowired
  private AppSecurityProperties appSecurityProperties;

  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public SecurityFilterChain authFilterChain(HttpSecurity httpSecurity) throws  Exception{
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity);
    return httpSecurity.formLogin(Customizer.withDefaults()).build();
  }

  @Bean
  public ProviderSettings providerSettings(AppSecurityProperties appSecurityProperties) {
    return ProviderSettings.builder().issuer(appSecurityProperties.getProviderUrl()).build();

  }

  @Bean
  public RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder){
    RegisteredClient registeredClient = RegisteredClient
            .withId("1").clientId(appSecurityProperties.getClientName())
            .clientSecret(passwordEncoder.encode(appSecurityProperties.getClientSecret()))
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .scopes((value) -> value.addAll(Arrays.asList("READ", "WRITE")))
            .tokenSettings(TokenSettings.builder()
                    .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
                    .accessTokenTimeToLive(Duration.ofSeconds(appSecurityProperties.getAccessTokenValiditySeconds()))
                    .refreshTokenTimeToLive(Duration.ofSeconds(appSecurityProperties.getRefreshTokenValiditySeconds()))
                    .build())
            .redirectUris((value) -> value.addAll(appSecurityProperties.getRedirectUris()))
            .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
            .build();
    return new InMemoryRegisteredClientRepository(Arrays.asList(registeredClient));
  }

  @Bean
  public JWKSource<SecurityContext> jwkSource(JwtKeyStoreProperties  properties) throws  Exception{
    char[] keyStorePassword = properties.getPassword().toCharArray();
    Resource jksLocation = properties.getJksLocation();
    String keypairAlias = properties.getKeypairAlias();
    InputStream inputStream = jksLocation.getInputStream();
    KeyStore keyStore = KeyStore.getInstance("JKS");
    keyStore.load(inputStream, keyStorePassword);
    RSAKey rsaKey = RSAKey.load(keyStore, keypairAlias, keyStorePassword);
    return new ImmutableJWKSet<>(new JWKSet(rsaKey));
  }

}
