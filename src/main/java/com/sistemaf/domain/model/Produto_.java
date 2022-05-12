package com.sistemaf.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto.class)
public abstract class Produto_ extends com.sistemaf.infrastructure.util.auditing.Auditing_ {

	public static volatile SingularAttribute<Produto, Long> id;
	public static volatile SingularAttribute<Produto, Fabricante> fabricante;
	public static volatile SingularAttribute<Produto, String> modelo;
	public static volatile SingularAttribute<Produto, String> descricao;
	public static volatile SingularAttribute<Produto, Float> valorUnitario;

}

