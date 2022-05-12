package com.sistemaf.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sistemaf.api.docs.controllers.NeighborhoodResourceOpenApi;
import com.sistemaf.api.dto.input.NeighborhoodInput;
import com.sistemaf.api.dto.manager.NeighborhoodMapper;
import com.sistemaf.api.dto.model.NeighborhoodDTO;
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
import com.sistemaf.domain.filter.BairroFilter;
import com.sistemaf.domain.model.Bairro;
import com.sistemaf.domain.service.BairroService;

import java.util.List;

@RestController
@RequestMapping(path = "/bairros", produces = MediaType.APPLICATION_JSON_VALUE)
public class BairroResource implements NeighborhoodResourceOpenApi {

	private NeighborhoodMapper dtoManager = NeighborhoodMapper.INSTANCE;

	@Autowired
	private BairroService bairroService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@Override
	@GetMapping
	@PreAuthorize("hasAuthority('12')")
	public Page<NeighborhoodDTO> pesquisar(BairroFilter bairroFilter, Pageable pageable){
		Page<Bairro> listBairros = bairroService.pesquisar(bairroFilter, pageable);
		List<NeighborhoodDTO> listNeighborhood = this.dtoManager.toCollectionModel(listBairros.getContent());
		Page<NeighborhoodDTO> neighborhoodList =
				new PageImpl<>( listNeighborhood, pageable, listBairros.getTotalElements());
		return neighborhoodList;
	}

	@Override
	@GetMapping("/{code}")
	@PreAuthorize("hasAuthority('12')")
	public ResponseEntity<NeighborhoodDTO> findByCode(@PathVariable Long code){
		Bairro bairro = bairroService.buscarPorCodigo(code);
		return bairro != null ? ResponseEntity.ok(dtoManager.toDto(bairro)) :ResponseEntity.notFound().build() ;
	}

	@Override
	@PostMapping
	@PreAuthorize("hasAuthority('10')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<NeighborhoodDTO> save(@Valid @RequestBody NeighborhoodInput inputBody, HttpServletResponse response){
		Bairro bairroSalvo = bairroService.salvar(dtoManager.toModel(inputBody));
		publisher.publishEvent(new RecursoCriarEvent(this, response, bairroSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoManager.toDto(bairroSalvo));
	}

	@Override
	@PutMapping("/{code}")
	@PreAuthorize("hasAuthority('10')")
	public ResponseEntity<NeighborhoodDTO> atualizar(@PathVariable Long code, @Valid @RequestBody NeighborhoodInput inputBody){
		Bairro bairroSalvo = this.bairroService.atualizar(code, this.dtoManager.toModel(inputBody));
		return ResponseEntity.ok(this.dtoManager.toDto(bairroSalvo));
	}

	@Override
	@DeleteMapping("/{code}")
	@PreAuthorize("hasAuthority('11')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long code) {
		bairroService.deletar(code);
	}
	
	
}
