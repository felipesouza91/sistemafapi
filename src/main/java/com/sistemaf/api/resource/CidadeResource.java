package com.sistemaf.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sistemaf.api.docs.controllers.CityResourceOpenApi;


import com.sistemaf.api.dto.input.CidadeInput;
import com.sistemaf.api.dto.manager.CityMapper;
import com.sistemaf.api.dto.model.CityDTO;
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
import com.sistemaf.domain.filter.CidadeFilter;
import com.sistemaf.domain.model.Cidade;
import com.sistemaf.domain.service.CidadeService;

@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeResource implements CityResourceOpenApi {

	@Autowired
	private CidadeService cidadeService;

	private CityMapper dtoManager = CityMapper.INSTANCE;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Override
	@GetMapping
	@PreAuthorize("hasAuthority('9')")
	public Page<CityDTO> listar(CidadeFilter cidadeFilter, Pageable pageable){
		Page<Cidade> listCity = cidadeService.filtrar(cidadeFilter,pageable);
		Page<CityDTO> listCityModel =
				new PageImpl<>(dtoManager.mapToDTO(listCity.getContent()), pageable, listCity.getTotalElements());
		return listCityModel;
	}
	
	@Override
	@GetMapping("/{code}")
	@PreAuthorize("hasAuthority('9')")
	public ResponseEntity<CityDTO> findByCode(@PathVariable Long code){
		Cidade cidade = cidadeService.buscaPorCodigo(code);
		CityDTO cityDTO = dtoManager.toDTO(cidade);
		return cidade != null ? ResponseEntity.ok(cityDTO) :ResponseEntity.notFound().build();
	}
	
	@Override
	@PostMapping
	@PreAuthorize("hasAuthority('7')")
	public ResponseEntity<CityDTO> criar(@Valid @RequestBody CidadeInput input, HttpServletResponse response){
		Cidade cidadeSalva = cidadeService.salvar(dtoManager.toModel(input));
		publisher.publishEvent(new RecursoCriarEvent(this, response, cidadeSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoManager.toDTO(cidadeSalva));
	}
	
	@Override
	@PutMapping("/{code}")
	@PreAuthorize("hasAuthority('7')")
	public ResponseEntity<CityDTO> atualizar(@PathVariable Long code, @Valid @RequestBody CidadeInput input){
		Cidade cidadeSalvo = this.cidadeService.atualizar(code, dtoManager.toModel(input));
		return ResponseEntity.ok(dtoManager.toDTO(cidadeSalvo));
	}

	@Override
	@DeleteMapping("/{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('8')")
	public void remover(@PathVariable Long code) {
		this.cidadeService.deletar(code);
	}
	
}
