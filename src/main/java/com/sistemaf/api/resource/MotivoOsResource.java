package com.sistemaf.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sistemaf.api.docs.controllers.ServiceOrderReasonResourceOpenApi;
import com.sistemaf.api.dto.input.ServiceOrderReasonInput;
import com.sistemaf.api.dto.manager.ServiceOrderReasonMapper;
import com.sistemaf.api.dto.model.ServiceOrderReasonModel;
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
import com.sistemaf.domain.filter.MotivoOsFilter;
import com.sistemaf.domain.model.MotivoOs;
import com.sistemaf.domain.service.MotivoOsService;

@RestController
@RequestMapping(path = "/motivososs",produces = MediaType.APPLICATION_JSON_VALUE)
public class MotivoOsResource implements ServiceOrderReasonResourceOpenApi {
	
	@Autowired
	private MotivoOsService motivoOsService;


	private ServiceOrderReasonMapper dtoManager = ServiceOrderReasonMapper.INSTANCE;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Override
	@GetMapping
	@PreAuthorize("hasAuthority('18')")
	public Page<ServiceOrderReasonModel> filtrar(MotivoOsFilter filter, Pageable pageable){
		Page<MotivoOs> list = motivoOsService.filtrar(filter, pageable);
		Page<ServiceOrderReasonModel> listModel = new PageImpl<>(dtoManager.toCollectionModel(list.getContent()), pageable, list.getTotalElements());
		return listModel;
	}
	
	@Override
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('18')")
	public ResponseEntity<ServiceOrderReasonModel> listar(@PathVariable Long codigo){
		MotivoOs motivoOs = motivoOsService.listarPorCodigo(codigo);
		return ResponseEntity.ok(dtoManager.toDto(motivoOs));
	}

	@Override
	@PostMapping
	@PreAuthorize("hasAuthority('16')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ServiceOrderReasonModel> salvar(@RequestBody @Valid ServiceOrderReasonInput input, HttpServletResponse response){
		MotivoOs motivoOsSalvo = motivoOsService.salvar(dtoManager.toModel(input));
		publisher.publishEvent(new RecursoCriarEvent(this, response, motivoOsSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoManager.toDto(motivoOsSalvo));
	}
	
	@Override
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('16')")
	public ResponseEntity<ServiceOrderReasonModel> atualizar(@PathVariable Long codigo, @Valid @RequestBody ServiceOrderReasonInput input){
		MotivoOs motivoOsSalva = motivoOsService.atualizar(codigo, dtoManager.toModel(input));
		return ResponseEntity.ok(dtoManager.toDto(motivoOsSalva));
	}
	
	@Override
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('17')")
	public void remover(@PathVariable Long codigo) {
		motivoOsService.remover(codigo);
	}
	
}
