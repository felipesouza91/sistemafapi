package com.sistemaf.api.resource;

import com.sistemaf.core.SistemFApiProperty;
import com.sistemaf.core.security.authorizationserver.AppSecurityProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/tokens", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Token")
@SecurityRequirement(name = "security_auth")
public class TokenResource {

	@Autowired
	private AppSecurityProperties appProperty;

	@Operation(summary = "Logoff application")
	@DeleteMapping("/revoke")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void revoke(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new	Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(appProperty.isEnableHttps());
		cookie.setPath(request.getContextPath() +"/oauth/token");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		response.setStatus(HttpStatus.NO_CONTENT.value());
	}
}
