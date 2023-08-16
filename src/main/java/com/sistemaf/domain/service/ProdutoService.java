package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.exception.EntityUsedException;
import com.sistemaf.domain.filter.ProdutoFiltro;
import com.sistemaf.domain.model.Produto;
import com.sistemaf.domain.projection.ResumoProduto;
import com.sistemaf.domain.repository.estoque.fabricante.FabricanteRepository;
import com.sistemaf.domain.repository.estoque.produto.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private FabricanteRepository fabricanteRepository;

	public Page<Produto> filtrar(ProdutoFiltro grupoFilter, Pageable pageable){
		return produtoRepository.filtrar(grupoFilter, pageable);
	}

	public Page<ResumoProduto> resumo(ProdutoFiltro produtoFilter, Pageable pageable) {
		return produtoRepository.resumo(produtoFilter, pageable);
	}

	public Produto listaPorCodigo(Long codigo) {
		return this.getProdutoOptional(codigo);
	}

	public Produto salvar(Produto produto) {
		findManufacturer(produto.getFabricante().getId());
		Optional<Produto> productOptional =
						this.produtoRepository.findByModeloWithoutSpaces(produto.getModelo());
		if(productOptional.isPresent() ) {
			throw new EntityUsedException("O produto já encontra-se cadastrado");
		}
		return produtoRepository.save(produto);
	}

	public void excluir(Long codigo) {
		this.getProdutoOptional(codigo);
		produtoRepository.deleteById(codigo);
	}

	public Produto atualizar(Long codigo,Produto produto) {
		findManufacturer(produto.getFabricante().getId());
		Produto produtoSalvo = this.getProdutoOptional(codigo);
		BeanUtils.copyProperties(produto, produtoSalvo, "id", "createdBy", "creationDate");
		return produtoRepository.save(produtoSalvo);
	}

	private Produto getProdutoOptional(Long codigo) {
		Optional<Produto> productOptional = produtoRepository.findById(codigo);
		return productOptional.orElseThrow(()-> new EntityNotFoundException("O Produto solicitado não existe"));
	}

	private void findManufacturer(Long manufacturerId) {
		this.fabricanteRepository.findById(manufacturerId).orElseThrow(() -> new BusinessException("O Fabricante não existe"));
	}
}
