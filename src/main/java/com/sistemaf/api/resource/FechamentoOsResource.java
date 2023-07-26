package com.sistemaf.api.resource;

import com.sistemaf.api.docs.controllers.ClosedOrderServiceResourceOpenApi;
import com.sistemaf.api.dto.input.ClosedOrderInput;
import com.sistemaf.api.dto.manager.ClosedOrderServiceMapper;
import com.sistemaf.api.dto.model.ClosedOrderDTO;
import com.sistemaf.domain.event.RecursoCriarEvent;
import com.sistemaf.domain.filter.FechamentoOsFilter;
import com.sistemaf.domain.model.FechamentoOs;
import com.sistemaf.domain.service.FechamentoOsService;
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

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/fechamentososs", produces = MediaType.APPLICATION_JSON_VALUE)
public class FechamentoOsResource implements ClosedOrderServiceResourceOpenApi {

	@Autowired
	private FechamentoOsService fechamentoOsService;

	private ClosedOrderServiceMapper dtoManager = ClosedOrderServiceMapper.INSTANCE;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Override
	@GetMapping
	@PreAuthorize("hasAuthority('24')")
	public Page<ClosedOrderDTO> list(FechamentoOsFilter filter, Pageable pageable){
		Page<FechamentoOs> list = fechamentoOsService.filtrar(filter, pageable);
		Page<ClosedOrderDTO> listModel =
				new PageImpl<>(dtoManager.mapDto(list.getContent()), pageable, list.getTotalElements());
		return listModel;
	}
	
	@Override
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('24')")
	public ResponseEntity<ClosedOrderDTO> list(@PathVariable Long codigo){
		FechamentoOs fechamentoOs = fechamentoOsService.listarPorCodigo(codigo);
		return ResponseEntity.ok(dtoManager.toDTO(fechamentoOs));
	}
	
	@Override
	@PostMapping
	@PreAuthorize("hasAuthority('22')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ClosedOrderDTO> salvar(@RequestBody @Valid ClosedOrderInput input, HttpServletResponse response){
		FechamentoOs fechamentoOsSalvo = fechamentoOsService.salvar(dtoManager.toDomain(input));
		publisher.publishEvent(new RecursoCriarEvent(this, response, fechamentoOsSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoManager.toDTO(fechamentoOsSalvo));
	}
	

	
	@Override
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('23')")
	public void remover(@PathVariable Long codigo) {
		fechamentoOsService.remover(codigo);
	}
	
	
}
