package com.sistemaf.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sistemaf.api.docs.controllers.DvrResourceOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
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
import com.sistemaf.domain.filter.DvrFilter;
import com.sistemaf.domain.model.Dvr;
import com.sistemaf.domain.service.DvrService;


@RestController
@RequestMapping(path = "/dvrs", produces = MediaType.APPLICATION_JSON_VALUE)
public class DvrResource implements DvrResourceOpenApi {
	
	@Autowired
	private DvrService dvrService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Override
	@GetMapping
	@PreAuthorize("hasAuthority('15')")
	public Page<Dvr> listar(DvrFilter dvrFilter, Pageable pageable){
		return dvrService.filtrar(dvrFilter, pageable);
	}
	
	@Override
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('15')")
	public ResponseEntity<Dvr> listar(@PathVariable Long codigo){
		Dvr dvr = dvrService.buscarPorCodigo(codigo);
		return dvr != null ? ResponseEntity.ok(dvr) : ResponseEntity.notFound().build() ;
	}

	@Override
	@PostMapping
	@PreAuthorize("hasAuthority('13')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Dvr> salvar(@RequestBody @Valid Dvr dvr, HttpServletResponse response){
		Dvr dvrSalvo = dvrService.salvar(dvr);
		publisher.publishEvent(new RecursoCriarEvent(this, response, dvrSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(dvrSalvo);
	}
	
	@Override
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('13')")
	public ResponseEntity<Dvr> atualizar(@PathVariable Long codigo, @Valid @RequestBody Dvr dvr){
		Dvr dvrSalvo = dvrService.atualizar(codigo, dvr);
		return ResponseEntity.ok(dvrSalvo);
	}
	
	@Override
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('14')")
	public void remover(@PathVariable Long codigo) {
		dvrService.remover(codigo);
	}

}	

