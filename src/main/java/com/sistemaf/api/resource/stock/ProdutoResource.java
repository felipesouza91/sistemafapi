package com.sistemaf.api.resource.stock;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sistemaf.api.docs.controllers.ProductResourceOpenApi;
import com.sistemaf.api.dto.input.ProductInput;
import com.sistemaf.api.dto.manager.ProductMapper;
import com.sistemaf.api.dto.model.ProductModel;
import com.sistemaf.api.dto.model.resume.ProductResumeModel;
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
import com.sistemaf.domain.filter.ProdutoFiltro;
import com.sistemaf.domain.model.Produto;
import com.sistemaf.domain.projection.ResumoProduto;
import com.sistemaf.domain.service.ProdutoService;

@RestController
@RequestMapping(path = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoResource implements ProductResourceOpenApi {

	@Autowired
	private ProdutoService produtoService;

	private ProductMapper dtoManager = ProductMapper.INSTANCE;
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('42')")
	public Page<ProductResumeModel> listar(ProdutoFiltro produtoFilter, Pageable pageable){
		Page<Produto> list = produtoService.filtrar(produtoFilter,pageable);
		Page<ProductResumeModel> listModel =
				new PageImpl<>(dtoManager.toCollectionResumeModel(list.getContent()), pageable, list.getTotalElements());
		return listModel;
	}
	
	@GetMapping(params ="resumo")
	@PreAuthorize("hasAuthority('42')")
	public Page<ResumoProduto> listarResumo(ProdutoFiltro produtoFilter, Pageable pageable){
		return produtoService.resumo(produtoFilter, pageable);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('42')")
	public ResponseEntity<ProductModel> listar(@PathVariable Long codigo){
		Produto produto = produtoService.listaPorCodigo(codigo);
		return ResponseEntity.ok(dtoManager.toDto(produto));
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('40')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProductModel> criar(@Valid @RequestBody ProductInput input, HttpServletResponse response){
		Produto produtoSalvo = produtoService.salvar(dtoManager.toModel(input));
		publisher.publishEvent(new RecursoCriarEvent(this, response, produtoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoManager.toDto(produtoSalvo));
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('40')")
	public ResponseEntity<ProductModel> atualizar(@PathVariable Long codigo, @Valid @RequestBody ProductInput input){
		Produto produtoSalvo = this.produtoService.atualizar(codigo, dtoManager.toModel(input));
		return ResponseEntity.ok(dtoManager.toDto(produtoSalvo));
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('41')")
	public void remover(@PathVariable Long codigo) {
		produtoService.excluir(codigo);
	} 
}
