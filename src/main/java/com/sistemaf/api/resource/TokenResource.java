package com.sistemaf.api.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaf.core.SistemFApiProperty;

@RestController
@RequestMapping(path = "/tokens", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Token")
public class TokenResource {

	@Autowired
	private SistemFApiProperty renthelpProperty;

	@Operation(summary = "Logoff application")
	@DeleteMapping("/revoke")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void revoke(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new	Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(renthelpProperty.getSeguranca().isEnableHttps()); 
		cookie.setPath(request.getContextPath() +"/oauth/token");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		response.setStatus(HttpStatus.NO_CONTENT.value());
	}
}
