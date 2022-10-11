package com.sistemaf.api.resource;

import com.sistemaf.api.dto.model.PermissionDto;
import com.sistemaf.domain.model.Permissao;
import com.sistemaf.domain.repository.security.PermissaoRepository;
import com.sistemaf.domain.usecases.permission.ListPermissionByCategoryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/permissao", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Permission")
@SecurityRequirement(name = "security_auth")
public class PermissaoResource {
	
	@Autowired
	private PermissaoRepository permissaoRepository;

	@Autowired
	private ListPermissionByCategoryUseCase listPermissionByCategoryUseCase;
	
	@Cacheable("permisao")
	@GetMapping
	@PreAuthorize("hasAuthority('33')")
	@Operation(summary = "Find Permissions")
	public List<Permissao> buscar(){
		return permissaoRepository.findAll(); 
	}

	@GetMapping("available")
	@Operation(summary = "Find available permissions to use")
	public Set<PermissionDto> findAvailablePermissions() {
		return this.listPermissionByCategoryUseCase.execute();
	}

}
