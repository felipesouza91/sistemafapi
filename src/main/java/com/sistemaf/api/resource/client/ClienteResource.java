package com.sistemaf.api.resource.client;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sistemaf.api.docs.controllers.ClientResourceOpenApi;
import com.sistemaf.api.dto.input.ClientInput;
import com.sistemaf.api.dto.manager.ClientMapper;
import com.sistemaf.api.dto.model.ClientModel;
import com.sistemaf.domain.event.RecursoCriarEvent;
import com.sistemaf.domain.filter.ClienteFilter;
import com.sistemaf.domain.model.Cliente;
import com.sistemaf.domain.service.ClienteService;

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

@RestController
@RequestMapping(path = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteResource implements ClientResourceOpenApi {

	@Autowired
	private ClienteService clienteService;

	private ClientMapper dtoManager = ClientMapper.INSTANCE;
	
	@Autowired
	private ApplicationEventPublisher publisher;
		
	@Override
	@GetMapping
	@PreAuthorize("hasAuthority('2')")
	public Page<ClientModel> filtrar(ClienteFilter clienteFilter, Pageable pageable){
		var list = clienteService.filtrar(clienteFilter, pageable);
		var listModel = new PageImpl<>(dtoManager.toCollectionDto(list.getContent()), pageable, list.getTotalElements());
		return  listModel;
	}
	
	@Override
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('2')")
	public ResponseEntity<ClientModel> listar(@PathVariable Long codigo){
		Cliente cliente = clienteService.listarPorCodigo(codigo);
		return ResponseEntity.ok(dtoManager.toDTO(cliente)) ;
	}

	@Override
	@PostMapping
	@PreAuthorize("hasAuthority('1')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ClientModel> salvar(@Valid @RequestBody ClientInput input, HttpServletResponse response){
		Cliente clienteSalvo = clienteService.salvar(dtoManager.toModel(input));
		publisher.publishEvent(new RecursoCriarEvent(this, response, clienteSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoManager.toDTO(clienteSalvo));
	}
	

	@Override
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('1')")
	public ResponseEntity<ClientModel> atualizar(@PathVariable Long codigo, @Valid @RequestBody ClientInput input){
		Cliente clienteSalvo = clienteService.atualizar(codigo, dtoManager.toModel(input));
		return ResponseEntity.ok(dtoManager.toDTO(clienteSalvo));
	}

	@Override
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('3')")
	public void remover(@PathVariable Long codigo){
		clienteService.deleteById(codigo);
	}
	
	
}
