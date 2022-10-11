package com.sistemaf.api.resource;

import com.sistemaf.api.docs.controllers.AccessGroupResourceOpenApi;
import com.sistemaf.api.dto.input.AccessGroupInputData;
import com.sistemaf.api.dto.manager.AccessGroupMapper;
import com.sistemaf.api.dto.model.AccessGroupDto;
import com.sistemaf.domain.event.RecursoCriarEvent;
import com.sistemaf.domain.model.GrupoAcesso;
import com.sistemaf.domain.projection.ResumoGrupoAcesso;
import com.sistemaf.domain.service.GrupoAcessoService;
import com.sistemaf.domain.usecases.accessgroup.LoadFormattedGroupAccessUseCase;
import com.sistemaf.domain.usecases.accessgroup.SaveAccessGroupUseCase;
import com.sistemaf.domain.usecases.accessgroup.UpdateAccessGroupUseCase;
import com.sistemaf.domain.usecases.permission.PermissionMapperUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/grupoacesso", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoAcessoResource implements AccessGroupResourceOpenApi {

	@Autowired
	private GrupoAcessoService grupoAcessoService;

	@Autowired
	private PermissionMapperUseCase permissionMapperUseCase;

	@Autowired
	private LoadFormattedGroupAccessUseCase loadFormattedGroupAccessUseCase;

	@Autowired
	private UpdateAccessGroupUseCase updateAccessGroupUseCase;

	@Autowired
	private SaveAccessGroupUseCase saveAccessGroupUseCase;

	private AccessGroupMapper dtoManager = AccessGroupMapper.INSTANCE;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	
	@Override
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('33')")
	public List<ResumoGrupoAcesso> listarResumo(){
		List<ResumoGrupoAcesso> resumos =  grupoAcessoService.resumo();
		return resumos;
	}
	

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('33')")
	public ResponseEntity<AccessGroupDto> findByCode(@PathVariable Long codigo) {
		AccessGroupDto accessGroupDto = loadFormattedGroupAccessUseCase.execute(codigo);
		return ResponseEntity.ok(accessGroupDto);
	}


	@Override
	@PostMapping
	@PreAuthorize("hasAuthority('31')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<AccessGroupDto> salvar(@Valid @RequestBody AccessGroupInputData input, HttpServletResponse response){
		GrupoAcesso grupoAcessoSalvo = saveAccessGroupUseCase.execute(input);
		AccessGroupDto groupDto = AccessGroupDto.builder().id(grupoAcessoSalvo.getId()).ativo(grupoAcessoSalvo.getAtivo())
										.descricao(grupoAcessoSalvo.getDescricao())
						.permissions(this.permissionMapperUseCase.toListDto(grupoAcessoSalvo.getPermissoes())).build();
		publisher.publishEvent(new RecursoCriarEvent(this, response, grupoAcessoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(groupDto);
	}
	
	@Override
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('31')")
	public ResponseEntity<AccessGroupDto> atualizar(@PathVariable Long codigo, @Valid @RequestBody AccessGroupInputData input){
		GrupoAcesso grupoAcesso = updateAccessGroupUseCase.execute(codigo, input);
		AccessGroupDto groupDto = AccessGroupDto.builder().id(grupoAcesso.getId()).ativo(grupoAcesso.getAtivo())
						.descricao(grupoAcesso.getDescricao())
						.permissions(this.permissionMapperUseCase.toListDto(grupoAcesso.getPermissoes())).build();
		return ResponseEntity.ok(groupDto);
	}
	
	@Override
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('32')")
	public void remover(@PathVariable Long codigo) {
		grupoAcessoService.remover(codigo);
	}
	
}
