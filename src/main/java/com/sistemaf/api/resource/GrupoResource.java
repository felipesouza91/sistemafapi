package com.sistemaf.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sistemaf.api.docs.controllers.GroupResourceOpenApi;
import com.sistemaf.api.dto.input.GroupInput;
import com.sistemaf.api.dto.manager.GroupMapper;
import com.sistemaf.api.dto.model.GroupModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
import com.sistemaf.domain.filter.GrupoFilter;
import com.sistemaf.domain.model.Grupo;
import com.sistemaf.domain.service.GrupoService;

@RestController
@RequestMapping(path = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Group")
public class GrupoResource implements GroupResourceOpenApi {

	@Autowired
	private GrupoService grupoService;

	private GroupMapper dtoManager = GroupMapper.INSTANCE;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Override
	@GetMapping
	@PreAuthorize("hasAuthority('6')")
	public Page<GroupModel> listar(GrupoFilter grupoFilter, Pageable pageable){
		Page<Grupo> groupList = grupoService.filtrar(grupoFilter, pageable);
		Page<GroupModel> listGroupModel = new PageImpl<>(
				dtoManager.mapToDTO(groupList.getContent()), pageable, groupList.getTotalElements());
		return listGroupModel;
	}
	
	@Override
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('6')")
	public ResponseEntity<GroupModel> lista(@PathVariable Long codigo){
		Grupo grupoBusca = grupoService.listaPorCodigo(codigo);
		return ResponseEntity.ok(dtoManager.toDTO(grupoBusca));
	}
		
	@Override
	@PostMapping
	@PreAuthorize("hasAuthority('4')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<GroupModel> salvar(@RequestBody @Valid GroupInput input, HttpServletResponse response){
		Grupo grupoSalvo = grupoService.salvar(dtoManager.toModel(input));
		publisher.publishEvent(new RecursoCriarEvent(this, response, grupoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoManager.toDTO(grupoSalvo));
	}
	
	@Override
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('4')")
	public ResponseEntity<GroupModel> atualizar(@PathVariable Long codigo, @Valid @RequestBody GroupInput input){
		Grupo grupoSalvo = this.grupoService.atualizar(codigo, dtoManager.toModel(input));
		return ResponseEntity.ok(dtoManager.toDTO(grupoSalvo));
	}
	
	@Override
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('5')")
	public void remover(@PathVariable Long codigo) {
		this.grupoService.excluir(codigo);
	}

	
}
