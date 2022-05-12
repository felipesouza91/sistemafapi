package com.sistemaf.api.resource;

import java.util.List;

import javax.validation.Valid;

import com.sistemaf.api.docs.controllers.PartitionResourceOpenApi;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaf.domain.model.Particao;
import com.sistemaf.domain.repository.ParticaoRepository;

@RestController
@RequestMapping(path = "/particao", produces = MediaType.APPLICATION_JSON_VALUE)
public class ParticaoResource implements PartitionResourceOpenApi {

	private ParticaoRepository particaoDao;
	
	@Override
	@GetMapping
	@PreAuthorize("hasAuthority('48')")
	public List<Particao> findAll(){
		return this.particaoDao.findAll();
	}
	
	@Override
	@PostMapping
	@PreAuthorize("hasAuthority('46')")
	public Particao save(@RequestBody @Valid Particao particao) {
		return this.particaoDao.save(particao);
	}
}
