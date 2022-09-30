package com.sistemaf.core.security;

import com.sistemaf.core.SistemFApiProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class ResourceServerConfig {

  @Autowired
  private SistemFApiProperty sistemFApiProperty;

  @Bean
  public SecurityFilterChain resourceSecurityFilterChain(HttpSecurity httpSecurity) throws  Exception{
      httpSecurity
        .authorizeRequests().antMatchers("/oauth2/**").authenticated()
        .and()
        .csrf().disable()
        .cors().and()
        .oauth2ResourceServer()
          .jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
    httpSecurity.logout( logoutConfig -> {
      logoutConfig.logoutSuccessHandler((httpServletRequest,httpServletResponse,authentication) ->{
                String returnTo = httpServletRequest.getParameter("redirectTo");
                if(!StringUtils.hasText(returnTo)) {
                  returnTo = sistemFApiProperty.getApiUrl();
                }
                httpServletResponse.setStatus(302);
                httpServletResponse.sendRedirect(returnTo);
              });
    });
    return httpSecurity.formLogin(customizer -> customizer.loginPage("/login")).build();

  }

  private JwtAuthenticationConverter jwtAuthenticationConverter( ){
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(jwt -> {
      List<String> authorities = jwt.getClaimAsStringList("authorities");
      if(authorities == null ) {
        return Collections.emptyList();
      }
      JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
      Collection<GrantedAuthority> grantedAuthorities = authoritiesConverter.convert(jwt);
      grantedAuthorities.addAll(authorities
          .stream()
          .map(SimpleGrantedAuthority::new)
          .collect(Collectors.toList()));
      return grantedAuthorities;
    });
    return converter;
  }
}
