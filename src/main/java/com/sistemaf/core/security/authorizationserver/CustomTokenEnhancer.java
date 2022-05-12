package com.sistemaf.core.security.authorizationserver;

import java.util.HashMap;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer  {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		if (authentication.getPrincipal() instanceof UsuarioSistema) {
			var authUser = (UsuarioSistema) authentication.getPrincipal();	
			var info = new HashMap<String, Object>();
			info.put("nome", authUser.getUsuario().getNome());
			info.put("id", authUser.getUsuario().getId());	
			var oAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
			
			oAuth2AccessToken.setAdditionalInformation(info);
		}
		
		return accessToken;
	}
}
