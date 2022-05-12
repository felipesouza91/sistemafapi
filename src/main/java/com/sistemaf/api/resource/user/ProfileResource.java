package com.sistemaf.api.resource.user;

import javax.validation.Valid;

import com.sistemaf.api.docs.controllers.ProfileResourceOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaf.api.dto.input.UpdatePasswordInput;
import com.sistemaf.core.security.SistemaFSecurity;
import com.sistemaf.domain.service.ProfileService;

@RestController
@RequestMapping(path = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileResource implements ProfileResourceOpenApi {
	
	@Autowired
	private ProfileService service;
	
	@Autowired
	private SistemaFSecurity security;

	@Override
	@PutMapping("/password")
	@PreAuthorize("isAuthenticated()")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePassword(@Valid @RequestBody UpdatePasswordInput input) {
		this.service.updatePassword(security.getUsuarioId(), input.getOldPassword(),
				input.getNewPassword(), input.getConfirmationNewPassword());
	} 
}
