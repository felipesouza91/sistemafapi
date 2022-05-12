package com.sistemaf.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sistemaf.api.docs.controllers.RecordingCheckResourceOpenApi;
import com.sistemaf.api.dto.input.RecordingCheckInput;
import com.sistemaf.api.dto.manager.RecordingCheckMapper;
import com.sistemaf.api.dto.model.RecordingCheckModel;
import com.sistemaf.api.dto.model.resume.RecordingCheckResumeModel;
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
import com.sistemaf.domain.filter.VerificarGravacaoFilter;
import com.sistemaf.domain.model.VerificacaoGravacao;
import com.sistemaf.domain.service.VerificacaoGravacaoService;

@RestController
@RequestMapping(path = "/verificacoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class VerificacaoGravacaoResource implements RecordingCheckResourceOpenApi {

	@Autowired
	private VerificacaoGravacaoService verificacaoGravacaoService;


	private RecordingCheckMapper dtoManager = RecordingCheckMapper.INSTANCE;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@Override
	@GetMapping
	@PreAuthorize("hasAuthority('2')")
	public Page<RecordingCheckModel> filtrar(VerificarGravacaoFilter filter, Pageable pageable){
		Page<VerificacaoGravacao> list = verificacaoGravacaoService.filtrar(filter, pageable);
		Page<RecordingCheckModel> listModel =
				new PageImpl<>(dtoManager.toCollectionModel(list.getContent()), pageable, list.getTotalElements());
		return listModel;
	}
	
	@Override
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('27')")
	public ResponseEntity<RecordingCheckModel> lista(@PathVariable Long codigo){
		VerificacaoGravacao verificagravacao = verificacaoGravacaoService.listaPorCodigo(codigo);
		return ResponseEntity.ok(dtoManager.toDto(verificagravacao));
	}
	
	@Override
	@GetMapping( params ="resumo")
	@PreAuthorize("hasAuthority('27')")
	public Page<RecordingCheckResumeModel> resumo(VerificarGravacaoFilter filter, Pageable pageable){
		var list = verificacaoGravacaoService.resumir(filter,pageable);
		var listPage = new PageImpl<>(dtoManager.toCollectionResumeModel(list.getContent()), pageable, list.getTotalElements());
		return listPage;
	}

	@Override
	@PostMapping
	@PreAuthorize("hasAuthority('25')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<RecordingCheckModel> salvar(@RequestBody @Valid RecordingCheckInput input, HttpServletResponse response){
		VerificacaoGravacao verificacaoSalva = verificacaoGravacaoService.salvar(dtoManager.toModel(input));
		publisher.publishEvent(new RecursoCriarEvent(this, response, verificacaoSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoManager.toDto(verificacaoSalva));
	}
	
	@Override
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('25')")
	public ResponseEntity<RecordingCheckModel> atualizar(@PathVariable Long codigo, @Valid @RequestBody RecordingCheckInput input){
		VerificacaoGravacao verificaGravacaoSalva = verificacaoGravacaoService.atualizar(codigo, dtoManager.toModel(input));
		return ResponseEntity.ok(dtoManager.toDto(verificaGravacaoSalva));
	}
	
	@Override
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('26')")
	public void remover(@PathVariable Long codigo) {
		verificacaoGravacaoService.remover(codigo);
	}
	
}
