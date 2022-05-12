package com.sistemaf.domain.repository.cliente;

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

import com.sistemaf.domain.filter.ClienteFilter;
import com.sistemaf.domain.model.Bairro;
import com.sistemaf.domain.model.Bairro_;
import com.sistemaf.domain.model.Cidade;
import com.sistemaf.domain.model.Cidade_;
import com.sistemaf.domain.model.Cliente;
import com.sistemaf.domain.model.Cliente_;
import com.sistemaf.domain.model.Endereco;
import com.sistemaf.domain.model.Endereco_;
import com.sistemaf.domain.model.Grupo;
import com.sistemaf.domain.model.Grupo_;

public class ClienteRepositoryImpl implements ClienteRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
		Root<Cliente> root = criteria.from(Cliente.class);

		Predicate[] predicates = criarRestricoes(clienteFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Cliente> query = manager.createQuery(criteria);
		adicionarRestricao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(clienteFilter));
		
	}
	
	private Predicate[] criarRestricoes(ClienteFilter clienteFilter, CriteriaBuilder builder, Root<Cliente> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(clienteFilter.getService())) {
			predicates.add(builder.equal(
					root.get(Cliente_.codigoService),
						clienteFilter.getService()));
		}		
		if(!StringUtils.isEmpty(clienteFilter.getAtivo())) {
			predicates.add(builder.equal(
					root.get(Cliente_.ativo),
						clienteFilter.getAtivo()));
		}

		if(!StringUtils.isEmpty(clienteFilter.getCodigoParticao())) {
			predicates.add(builder.equal(
					root.get(Cliente_.codigoParticao),
						clienteFilter.getCodigoParticao()));
		}

		if(!StringUtils.isEmpty(clienteFilter.getFantazia())) {
			predicates.add(builder.like(
					builder.lower(root.get(Cliente_.fantazia)),
						"%"+clienteFilter.getFantazia().toLowerCase()+"%"));
		}

		if(!StringUtils.isEmpty(clienteFilter.getRazaoSocial())) {
			predicates.add(builder.like(
					builder.lower(root.get(Cliente_.razaoSocial)),
						"%"+clienteFilter.getRazaoSocial().toLowerCase()+"%"));
		}

		if(!StringUtils.isEmpty(clienteFilter.getEndereco())) {	
			Join<Cliente, Endereco> join = root.join(Cliente_.endereco);
			predicates.add(builder.like(
						builder.lower(join.get(Endereco_.rua)),
							"%"+clienteFilter.getEndereco().toLowerCase()+"%" ));
		}

		if(!StringUtils.isEmpty(clienteFilter.getBairro())) {
			Join<Cliente, Endereco> joinEndereco = root.join(Cliente_.endereco);
			Join<Endereco, Bairro> joinBairro = joinEndereco.join(Endereco_.bairro);
			predicates.add(builder.like(
					builder.lower(joinBairro.get(Bairro_.nome)),
					"%"+clienteFilter.getBairro().toLowerCase()+"%"));
		}

		if(!StringUtils.isEmpty(clienteFilter.getCidade())) {
			Join<Cliente, Endereco> joinEndereco = root.join(Cliente_.endereco);
			Join<Endereco, Bairro> joinBairro = joinEndereco.join(Endereco_.bairro);
			Join<Bairro, Cidade> joinCidade = joinBairro.join(Bairro_.cidade);
			
			predicates.add(builder.like(
					builder.lower(joinCidade.get(Cidade_.nome)),
						"%"+clienteFilter.getCidade().toLowerCase()+"%" ));
		}
		
		if(!StringUtils.isEmpty(clienteFilter.getDominio())) {
			predicates.add(builder.like(
					builder.lower(root.get(Cliente_.dominio)),
						"%"+clienteFilter.getDominio().toLowerCase()+"%"));
		}
		
		if(!StringUtils.isEmpty(clienteFilter.getNomeGrupo())) {
			Join<Cliente, Grupo> joinGrupo = root.join(Cliente_.grupo);
			predicates.add(builder.like(
					builder.lower(joinGrupo.get(Grupo_.nome)),
						"%"+clienteFilter.getNomeGrupo().toLowerCase()+"%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Long total(ClienteFilter clienteFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class); 
		Root<Cliente> root = criteria.from(Cliente.class);
		Predicate[] predicates = criarRestricoes(clienteFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}


	private void adicionarRestricao(TypedQuery<Cliente> query, Pageable pageable) {
		int paginaAtual =pageable.getPageNumber(); 
		int totalPagina = pageable.getPageSize();
		int primeiroRegistroPagina = paginaAtual * totalPagina;
		query.setFirstResult(primeiroRegistroPagina);
		query.setMaxResults(totalPagina);
	}

}
