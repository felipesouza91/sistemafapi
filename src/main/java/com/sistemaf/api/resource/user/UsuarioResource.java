package com.sistemaf.api.resource.user;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sistemaf.api.docs.controllers.UserResourceOpenApi;
import com.sistemaf.api.dto.input.UserInput;
import com.sistemaf.api.dto.manager.UserMapper;
import com.sistemaf.api.dto.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sistemaf.domain.event.RecursoCriarEvent;
import com.sistemaf.domain.filter.UsuarioFilter;
import com.sistemaf.domain.model.Usuario;
import com.sistemaf.domain.projection.UserSimpleModel;
import com.sistemaf.domain.service.UsuarioService;

@RestController
@RequestMapping(path = "/usuario",produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioResource implements UserResourceOpenApi {

	@Autowired
	private UsuarioService usuarioService;


	private UserMapper dtoManager = UserMapper.INSTANCE;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Override
	@GetMapping
	@PreAuthorize("hasAuthority('30')")
	public Page<UserModel> filtar(UsuarioFilter filter, Pageable page){
		var list = usuarioService.filtro(filter, page);
		var lisModel = new PageImpl<>(dtoManager.toCollectionModel(list.getContent()), page, list.getTotalElements());
		return lisModel;
	}
	
	@Override
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('30')")
	public List<UserSimpleModel> resumo(){
		return usuarioService.resumo();
	}
	
	@Override
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('30')")
	public ResponseEntity<UserModel> buscarPorCodigo(@PathVariable Long codigo) {
		Usuario usuarioBuscado = usuarioService.buscarPorCodigo(codigo);
		return  ResponseEntity.ok(dtoManager.toDto(usuarioBuscado));
	}
	
	@Override
	@PostMapping
	@PreAuthorize("hasAuthority('28')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UserModel> salvar(@Valid @RequestBody UserInput input, HttpServletResponse response){
		Usuario usuarioSalvo = usuarioService.salvar(dtoManager.toModel(input));
		publisher.publishEvent(new RecursoCriarEvent(this, response, usuarioSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoManager.toDto(usuarioSalvo));
	}
	
	@Override
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('28')")
	public ResponseEntity<UserModel> atualizar(@PathVariable Long codigo, @Valid @RequestBody UserInput input){
		Usuario usuarioSalvo = usuarioService.atualizar(codigo, dtoManager.toModel(input));
		return ResponseEntity.ok(dtoManager.toDto(usuarioSalvo));
	}
}
