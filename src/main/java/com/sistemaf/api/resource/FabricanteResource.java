package com.sistemaf.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sistemaf.api.docs.controllers.ManufacturerResourceOpenApi;
import com.sistemaf.api.dto.input.ManufacturerInput;
import com.sistemaf.api.dto.manager.ManufacturerMapper;
import com.sistemaf.api.dto.model.ManufacturerDTO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaf.domain.event.RecursoCriarEvent;
import com.sistemaf.domain.model.Fabricante;
import com.sistemaf.domain.service.FabricanteService;

@RestController
@RequestMapping(path = "/fabricantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class FabricanteResource implements ManufacturerResourceOpenApi {

	@Autowired
	private FabricanteService fabService;

	private ManufacturerMapper dtoManager = ManufacturerMapper.INSTANCE;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@Override
	@GetMapping
	@PreAuthorize("hasAuthority('39')")
	public ResponseEntity<Page<ManufacturerDTO>> getAll(@RequestParam(required = false) String nome, Pageable page) {
		Page<Fabricante> listFab = this.fabService.getAll(nome, page);
		Page<ManufacturerDTO> listManufacturerModel =
				new PageImpl<>(dtoManager.mapDto(listFab.getContent()), page, listFab.getTotalElements());
		return ResponseEntity.ok(listManufacturerModel);
	}
	
	@Override
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('39')")
	public ResponseEntity<ManufacturerDTO> getById(@PathVariable Long id) {
		Fabricante fab = this.fabService.getById(id);
		return ResponseEntity.ok(dtoManager.toDTO(fab)) ;
	}
	
	@Override
	@PostMapping
	@PreAuthorize("hasAuthority('37')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ManufacturerDTO> save(@Valid @RequestBody ManufacturerInput input, HttpServletResponse response) {
		Fabricante fabSalvo = this.fabService.save(dtoManager.toModel(input));
		publisher.publishEvent( new RecursoCriarEvent(this, response, fabSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoManager.toDTO(fabSalvo));
	}

	@Override
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('37')")
	public ResponseEntity<ManufacturerDTO> update(@PathVariable Long id, @Valid @RequestBody ManufacturerInput input) {
		Fabricante fabUpdate = this.fabService.update(id, dtoManager.toModel(input));
		return ResponseEntity.ok(dtoManager.toDTO(fabUpdate));
	}
	
	@Override
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('38')")
	public void delete(@PathVariable Long id) {
		this.fabService.remove(id);
	}
}
