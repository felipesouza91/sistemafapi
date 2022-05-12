package com.sistemaf.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sistemaf.api.docs.controllers.ServiceOrderResourceOpenApi;
import com.sistemaf.api.dto.input.ServiceOrderInput;
import com.sistemaf.api.dto.manager.ServiceOrderMapper;
import com.sistemaf.api.dto.model.ServiceOrderModel;
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
import com.sistemaf.domain.filter.OrdemServicoFilter;
import com.sistemaf.domain.model.OrdemServico;
import com.sistemaf.domain.projection.ResumOrdemServico;
import com.sistemaf.domain.service.OrdemServicoService;

@RestController
@RequestMapping(path = "/ordensservicos", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdemServicoResource implements ServiceOrderResourceOpenApi {

	@Autowired
	private OrdemServicoService ordemService;

	private ServiceOrderMapper dtoManager = ServiceOrderMapper.INSTANCE;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Override
	@GetMapping
	@PreAuthorize("hasAuthority('2')")
	public Page<ServiceOrderModel> filtrar(OrdemServicoFilter filter, Pageable pageable){
		Page<OrdemServico> list = ordemService.filtrar(filter, pageable);
		Page<ServiceOrderModel> listModel = new PageImpl<>(dtoManager.mapToDTO(list.getContent()), pageable, list.getTotalElements());
		return listModel;
	}
	
	@Override
	@GetMapping(params ="resumo")
	@PreAuthorize("hasAuthority('21')")
	public Page<ResumOrdemServico> resumir(OrdemServicoFilter filter, Pageable pageable){
		return ordemService.resumir(filter, pageable);
	}
	
	@Override
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('21')")
	public ResponseEntity<ServiceOrderModel> lista(@PathVariable Long codigo){
		OrdemServico ordemServico = ordemService.listaPorCodigo(codigo);
		return ResponseEntity.ok(dtoManager.toDto(ordemServico));
	}

	@Override
	@PostMapping
	@PreAuthorize("hasAuthority('19')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ServiceOrderModel> salvar(@RequestBody @Valid ServiceOrderInput input, HttpServletResponse response){
		OrdemServico osSalva = ordemService.salvar(dtoManager.toModel(input));
		publisher.publishEvent(new RecursoCriarEvent(this, response, osSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoManager.toDto(osSalva));
	}

	@Override
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('19')")
	public ResponseEntity<ServiceOrderModel> atualizar(@PathVariable Long codigo, @Valid @RequestBody ServiceOrderInput input){
		OrdemServico osSalva = ordemService.atualizar(codigo, dtoManager.toModel(input));
		return ResponseEntity.ok(dtoManager.toDto(osSalva));
	}
	
	@Override
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('20')")
	public void remover(@PathVariable Long codigo) {
		ordemService.remover(codigo);
	}
	
}
