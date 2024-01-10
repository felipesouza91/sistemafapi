package com.sistemaf.domain.repository.estoque.produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.sistemaf.domain.filter.ProdutoFiltro;
import com.sistemaf.domain.model.Fabricante_;
import com.sistemaf.domain.model.Produto;
import com.sistemaf.domain.model.Produto_;
import com.sistemaf.domain.projection.ResumoProduto;
import com.sistemaf.infrastructure.util.interfaces.FilterUtil;
import com.sistemaf.infrastructure.util.interfaces.PageableUtil;

public class ProdutoRepositoryImpl extends PageableUtil implements ProdutoRepositoryQuery, FilterUtil<ProdutoFiltro, Produto> {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Produto> filtrar(ProdutoFiltro filtro, Pageable page) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);
		Root<Produto> root = criteria.from(Produto.class);
		Predicate[] predicates = this.criarRestricoes(filtro, builder, root);
		criteria.where(predicates);
		TypedQuery<Produto> query = manager.createQuery(criteria);
		super.adicionarRestricoesDePaginacao(query, page);
		return new PageImpl<>(query.getResultList(), page,
				super.total(manager, predicates, root, Produto.class));
	}

	@Override
	public Page<ResumoProduto> resumo(ProdutoFiltro filtro, Pageable page) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoProduto> criteria = builder.createQuery(ResumoProduto.class);
		Root<Produto> root = criteria.from(Produto.class);
		criteria.select(builder.construct(ResumoProduto.class, root.get(Produto_.id), root.get(Produto_.modelo),
				root.get(Produto_.valorUnitario), root.get(Produto_.fabricante).get(Fabricante_.id),
					root.get(Produto_.fabricante).get(Fabricante_.descricao)));
		Predicate[] predicates = this.criarRestricoes(filtro, builder, root);
		criteria.where(predicates);
		TypedQuery<ResumoProduto> query = manager.createQuery(criteria);
		super.adicionarRestricoesDePaginacao(query, page);
		return new PageImpl<>(query.getResultList(), page,
				super.total(manager, predicates, root, Produto.class));
	}

	@Override
	public Predicate[] criarRestricoes(ProdutoFiltro filter, CriteriaBuilder builder, Root<Produto> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(filter.getModelo())) {
			predicates.add(builder.like(builder.lower(root.get(Produto_.modelo)),
						"%"+filter.getModelo().toLowerCase()+"%"));
		}
		if(!StringUtils.isEmpty(filter.getIdFabricante())) {
			predicates.add(builder.equal(root.get(Produto_.fabricante).get(Fabricante_.id),
					filter.getIdFabricante()));
		}
		if(!StringUtils.isEmpty(filter.getNomeFabricante())) {
			predicates.add(builder.like(builder.lower(root.get(Produto_.fabricante).get(Fabricante_.descricao)),
					"%"+filter.getNomeFabricante().toLowerCase()+"%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@Override
	public Optional<Produto> findByModeloWithoutSpaces(String name) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);
		Root<Produto> root = criteria.from(Produto.class);
		criteria.where(builder.like(builder.function("REPLACE", String.class, root.get(Produto_.modelo),
					builder.literal(" "), builder.literal("")),
		"%"+name.replaceAll(" ", "")+"%"));
		TypedQuery<Produto> query = manager.createQuery(criteria);
		var result = query.getResultList();
		return !result.isEmpty() ? Optional.of(result.get(0)) : Optional.empty();
	}

}
