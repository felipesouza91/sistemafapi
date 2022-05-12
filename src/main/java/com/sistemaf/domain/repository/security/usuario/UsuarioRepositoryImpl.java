package com.sistemaf.domain.repository.security.usuario;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.sistemaf.domain.filter.UsuarioFilter;
import com.sistemaf.domain.model.GrupoAcesso;
import com.sistemaf.domain.model.GrupoAcesso_;
import com.sistemaf.domain.model.Usuario;
import com.sistemaf.domain.model.Usuario_;
import com.sistemaf.domain.projection.UserSimpleModel;


public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Usuario> filtar(UsuarioFilter filter, Pageable page) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		
		criteria.where(predicates);
		
		TypedQuery<Usuario> query = manager.createQuery(criteria);
		adicionarRestricoedePaginacao(query, page);
		return new PageImpl<>(query.getResultList(), page, total(filter));
	}

	@Override
	public List<UserSimpleModel> resumo() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<UserSimpleModel> criteria = builder.createQuery(UserSimpleModel.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		
		criteria.select(builder.construct(UserSimpleModel.class, root.get(Usuario_.id),
				root.get(Usuario_.ativo),
				root.get(Usuario_.nome), root.get(Usuario_.apelido),
				root.get(Usuario_.grupoAcesso).get(GrupoAcesso_.id),
					root.get(Usuario_.grupoAcesso).get(GrupoAcesso_.descricao)));
		TypedQuery<UserSimpleModel> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(UsuarioFilter filter, CriteriaBuilder builder, Root<Usuario> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(filter.getApelido())) {
			predicates.add(builder.like(
					builder.lower(root.get(Usuario_.apelido)),
						"%"+filter.getApelido().toLowerCase()+"%"));
		}
		if(!StringUtils.isEmpty(filter.getFuncionario())) {
			predicates.add(builder.like(
					builder.lower(root.get(Usuario_.nome)),
						"%"+filter.getFuncionario().toLowerCase()+"%"));
		}
		if(!StringUtils.isEmpty(filter.getGrupoAcesso())) {
			Join<Usuario, GrupoAcesso> join = root.join(Usuario_.grupoAcesso);
			predicates.add(builder.like(
					builder.lower(join.get(GrupoAcesso_.descricao)),
						"%"+filter.getGrupoAcesso().toLowerCase()+"%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);	
	}

	private Long total(UsuarioFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class); 
		Root<Usuario> root = criteria.from(Usuario.class);
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoedePaginacao(TypedQuery<Usuario> query, Pageable page) {
		int paginaAtual =page.getPageNumber(); 
		int totalPagina = page.getPageSize();
		int primeiroRegistroPagina = paginaAtual * totalPagina;
		
		query.setFirstResult(primeiroRegistroPagina);
		query.setMaxResults(totalPagina);
		
	}

}
