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
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.InputStream;
import java.security.KeyStore;
import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
public class AuthorizationServerConfig {

  @Autowired
  private AppSecurityProperties appSecurityProperties;

  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public SecurityFilterChain authFilterChain(HttpSecurity httpSecurity) throws  Exception{
    OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer =
            new OAuth2AuthorizationServerConfigurer<>();
    RequestMatcher endpointsMatcher = authorizationServerConfigurer
            .getEndpointsMatcher();
    authorizationServerConfigurer.authorizationEndpoint(customizer -> customizer.consentPage("/oauth2/consent"))
                    .getEndpointsMatcher();
    httpSecurity
            .requestMatcher(endpointsMatcher)
            .authorizeRequests(authorizeRequests ->
                    authorizeRequests.anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
            .apply(authorizationServerConfigurer);
    return httpSecurity.formLogin(customizer -> customizer.loginPage("/login")).build();
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
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .scopes((value) -> value.addAll(Arrays.asList("READ", "WRITE")))
            .tokenSettings(TokenSettings.builder()
                    .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
                    .accessTokenTimeToLive(Duration.ofMinutes(appSecurityProperties.getAccessTokenValidity()))
                    .reuseRefreshTokens(false)
                    .refreshTokenTimeToLive(Duration.ofMinutes(appSecurityProperties.getRefreshTokenValidity()))
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

  @Bean
  public OAuth2TokenCustomizer<JwtEncodingContext> oAuth2TokenCustomizer() {
      return context -> {
        Authentication principal = context.getPrincipal();
        if(principal.getPrincipal() instanceof  UsuarioSistema) {
          UsuarioSistema user = (UsuarioSistema) principal.getPrincipal();
          context.getClaims().claim("userId", user.getUsuario().getId().toString());
          context.getClaims().claim("userName", user.getUsuario().getNome());
          context.getClaims().claim("authorities", user.getAuthorities()
                  .stream().map((item) -> item.getAuthority()).collect(Collectors.toSet()));
        }

      };
  }

  @Bean
  public JdbcOAuth2AuthorizationConsentService consentService(JdbcOperations jdbcOperations,
                                                              RegisteredClientRepository registeredClientRepository   ) {
    return new JdbcOAuth2AuthorizationConsentService(jdbcOperations, registeredClientRepository);
  }
}
