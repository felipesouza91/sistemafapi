package com.sistemaf.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sistemaf.api.docs.controllers.AccessGroupResourceOpenApi;
import com.sistemaf.api.dto.input.AccessGroupInput;
import com.sistemaf.api.dto.manager.AccessGroupMapper;
import com.sistemaf.api.dto.model.AccessGroupModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaf.domain.event.RecursoCriarEvent;
import com.sistemaf.domain.model.GrupoAcesso;
import com.sistemaf.domain.projection.ResumoGrupoAcesso;
import com.sistemaf.domain.service.GrupoAcessoService;

@RestController
@RequestMapping(path = "/grupoacesso", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoAcessoResource implements AccessGroupResourceOpenApi {

	@Autowired
	private GrupoAcessoService grupoAcessoService;


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
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('33')")
	public ResponseEntity<AccessGroupModel> porCodigo(@PathVariable Long codigo) {
		GrupoAcesso grupoAcesso = grupoAcessoService.buscarPorCodigo(codigo);
		return ResponseEntity.ok(dtoManager.toDTO(grupoAcesso));
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
