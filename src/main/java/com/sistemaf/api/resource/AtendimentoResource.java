package com.sistemaf.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaf.domain.event.RecursoCriarEvent;
import com.sistemaf.domain.filter.AtendimentoFilter;
import com.sistemaf.domain.model.Atendimento;
import com.sistemaf.domain.model.RelatoAtendimento;
import com.sistemaf.domain.projection.ResumoAtendimento;
import com.sistemaf.domain.service.AtendimentoService;
import com.sistemaf.domain.service.RelatoAtendimentoService;

@RestController
@RequestMapping(path = "/atendimentos", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Treatment", hidden = true)
public class AtendimentoResource {

	@Autowired
	private AtendimentoService atendimentoService;
	
	@Autowired
	private RelatoAtendimentoService relatoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('36')")
	public Page<Atendimento> listarTodos(AtendimentoFilter filter, Pageable page){
		return atendimentoService.listar(filter, page);
	}
	
	@GetMapping(params = "resumo" )
	@PreAuthorize("hasAuthority('36')")
	public Page<ResumoAtendimento> resumo(AtendimentoFilter filter, Pageable page){
		return atendimentoService.resumo(filter, page);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('36')")
	public ResponseEntity<Atendimento> buscarPorCodigo(@PathVariable Long codigo){
		Atendimento atendimento = atendimentoService.buscarPorCodigo(codigo);
		return atendimento != null ? ResponseEntity.ok(atendimento) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{idAtendimento}/relatos")
	@PreAuthorize("hasAuthority('36')")
	public ResponseEntity<List<RelatoAtendimento>> buscarAtendimento(@PathVariable Long idAtendimento) {
		List<RelatoAtendimento> list = relatoService.findAtendimento(idAtendimento);
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('34')")
	public ResponseEntity<Atendimento> salvar(@Valid @RequestBody Atendimento atendimento,
				HttpServletResponse response) {
		Atendimento atendimentoSalvo = atendimentoService.salvar(atendimento);
		publisher.publishEvent(new RecursoCriarEvent(this, response, atendimentoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(atendimentoSalvo);
	}
	
	@PostMapping("/{idAtendimento}/relatos")
	@PreAuthorize("hasAuthority('34')")
	public ResponseEntity<RelatoAtendimento> adicionarRelato(@PathVariable Long idAtendimento,
				@Valid @RequestBody RelatoAtendimento relato,
					HttpServletResponse response) {
		RelatoAtendimento relatoSalvo = relatoService.salvar(idAtendimento, relato);
		publisher.publishEvent(new RecursoCriarEvent(this, response, relatoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(relatoSalvo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('34')")
	public ResponseEntity<Atendimento> atualizar(@PathVariable Long codigo,@Valid @RequestBody Atendimento atendimento) {
		Atendimento atendimentoSalvo = atendimentoService.atualizar(codigo, atendimento);
		return ResponseEntity.ok(atendimentoSalvo);
	}
	
	@PutMapping("/relato/{codigo}")
	@PreAuthorize("hasAuthority('34')")
	public ResponseEntity<RelatoAtendimento> atualizarRelato(@PathVariable Long codigo, 
			@RequestBody RelatoAtendimento relato) {
		RelatoAtendimento relatoSalvo = relatoService.atualizar(codigo, relato);
		return ResponseEntity.ok(relatoSalvo);
	}
}
