package com.sistemaf.api.resource;

import com.sistemaf.api.docs.controllers.AccessGroupResourceOpenApi;
import com.sistemaf.api.dto.input.AccessGroupInput;
import com.sistemaf.api.dto.manager.AccessGroupMapper;
import com.sistemaf.api.dto.model.AccessGroupDto;
import com.sistemaf.api.dto.model.AccessGroupModel;
import com.sistemaf.domain.event.RecursoCriarEvent;
import com.sistemaf.domain.model.GrupoAcesso;
import com.sistemaf.domain.projection.ResumoGrupoAcesso;
import com.sistemaf.domain.service.GrupoAcessoService;
import com.sistemaf.domain.usecases.LoadFormattedGroupAccessUseCase;
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
	private LoadFormattedGroupAccessUseCase loadFormattedGroupAccessUseCase;

	private AccessGroupMapper dtoManager = AccessGroupMapper.INSTANCE;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Override
	@GetMapping
	@PreAuthorize("hasAuthority('33')")
	public List<AccessGroupModel> listar(){
		return dtoManager.mapToDTO(grupoAcessoService.filtrar());
	}
	
	@Override
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('33')")
	public List<ResumoGrupoAcesso> listarResumo(){
		List<ResumoGrupoAcesso> resumos =  grupoAcessoService.resumo();
		return resumos;
	}
	
	@Override
	@GetMapping("/{codigo}/test")
	@PreAuthorize("hasAuthority('33')")
	public ResponseEntity<AccessGroupModel> porCodigo(@PathVariable Long codigo) {
		GrupoAcesso grupoAcesso = grupoAcessoService.buscarPorCodigo(codigo);
		return ResponseEntity.ok(dtoManager.toDTO(grupoAcesso));
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
	public ResponseEntity<AccessGroupModel> salvar(@Valid @RequestBody AccessGroupInput input, HttpServletResponse response){
		GrupoAcesso grupoAcessoSalvo = grupoAcessoService.salvar(dtoManager.toModel(input));
		publisher.publishEvent(new RecursoCriarEvent(this, response, grupoAcessoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoManager.toDTO(grupoAcessoSalvo));
	}
	
	@Override
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('31')")
	public ResponseEntity<AccessGroupModel> atualizar(@PathVariable Long codigo, @Valid @RequestBody AccessGroupInput input){
		GrupoAcesso grupoAcessoSalvo = grupoAcessoService.atualizar(codigo, dtoManager.toModel(input));
		return ResponseEntity.ok(dtoManager.toDTO(grupoAcessoSalvo));
	}
	
	@Override
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('32')")
	public void remover(@PathVariable Long codigo) {
		grupoAcessoService.remover(codigo);
	}
	
}
