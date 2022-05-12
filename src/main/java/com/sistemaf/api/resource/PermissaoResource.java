package com.sistemaf.api.resource;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaf.domain.model.Permissao;
import com.sistemaf.domain.repository.security.PermissaoRepository;

@RestController
@RequestMapping(path = "/permissao", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Permission")
public class PermissaoResource {
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Cacheable("permisao")
	@GetMapping
	@PreAuthorize("hasAuthority('33')")
	@Operation(summary = "Find Permissions")
	public List<Permissao> buscar(){
		return permissaoRepository.findAll(); 
	}
}
