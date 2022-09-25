package com.sistemaf.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class SistemaFSecurity {

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public boolean isAutenticado() {
		return getAuthentication().isAuthenticated();
	}

	public Long getUsuarioId() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		return jwt.getClaim("id");
	}

	public boolean hasAuthority(String authority) {
		System.out.println(getAuthentication().getAuthorities().stream().anyMatch(autho -> autho.getAuthority().equals(authority)));
		return getAuthentication().getAuthorities().stream().anyMatch(autho -> autho.getAuthority().equals(authority));
	}

	public boolean temScopeWrite() {
		return hasAuthority("SCOPE_WRITE");
	}

	public boolean temScopeRead() {
		return hasAuthority("SCOPE_READ");
	}

}
