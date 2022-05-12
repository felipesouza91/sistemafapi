package com.sistemaf.infrastructure.util.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.sistemaf.core.SistemFApiProperty;
import com.sistemaf.infrastructure.exception.SistemaException;


@Component
@Profile("int-api")
public class ApiConnection {
	
	@Autowired
	private SistemFApiProperty apiProperty;

	@Autowired
	private RestTemplate restTemplate;
	
	public JSONObject getAuthentication(String path) {
		try {
			String url = getUrl(path);
			JSONObject authentication;
	    	MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();     
	    	body.add("grant_type", "password");
	    	body.add("username", "souza");
	    	body.add("password", "3162");
	    	body.add("portal", "true");
			HttpEntity<?> httpEntity = new HttpEntity<Object>(body, null);
	        ResponseEntity<String> a = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
	        authentication = new JSONObject(a.getBody());
			return authentication;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private String getUrl(String path) {
		if(apiProperty.getApiIntegrationUrl().isEmpty()) {
			throw new SistemaException("Integração nao esta habilitada");
		}
		return apiProperty.getApiIntegrationUrl() + path;
	}
}
