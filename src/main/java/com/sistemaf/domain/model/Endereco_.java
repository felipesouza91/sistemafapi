package com.sistemaf.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Endereco.class)
public abstract class Endereco_ {

	public static volatile SingularAttribute<Endereco, String> complemento;
	public static volatile SingularAttribute<Endereco, Long> numero;
	public static volatile SingularAttribute<Endereco, Bairro> bairro;
	public static volatile SingularAttribute<Endereco, String> referencia;
	public static volatile SingularAttribute<Endereco, String> rua;

}

