package com.sistemaf.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ {

	public static volatile SingularAttribute<Cliente, String> codigoParticao;
	public static volatile SingularAttribute<Cliente, Boolean> ativo;
	public static volatile SingularAttribute<Cliente, String> telefone1;
	public static volatile SingularAttribute<Cliente, Endereco> endereco;
	public static volatile SingularAttribute<Cliente, String> dominio;
	public static volatile SingularAttribute<Cliente, Grupo> grupo;
	public static volatile SingularAttribute<Cliente, String> fantazia;
	public static volatile SingularAttribute<Cliente, String> telefone2;
	public static volatile SingularAttribute<Cliente, Integer> codigoService;
	public static volatile ListAttribute<Cliente, Particao> listParticoes;
	public static volatile SingularAttribute<Cliente, Long> id;
	public static volatile ListAttribute<Cliente, ClienteInformacao> informacoes;
	public static volatile SingularAttribute<Cliente, String> razaoSocial;

}

