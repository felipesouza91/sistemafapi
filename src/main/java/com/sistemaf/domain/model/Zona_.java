package com.sistemaf.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.sistemaf.domain.model.custompk.ZonaPk;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Zona.class)
public abstract class Zona_ {

	public static volatile SingularAttribute<Zona, String> observacao;
	public static volatile SingularAttribute<Zona, Produto> produto;
	public static volatile SingularAttribute<Zona, Particao> particao;
	public static volatile SingularAttribute<Zona, ZonaPk> id;
	public static volatile SingularAttribute<Zona, Long> idZona;
	public static volatile SingularAttribute<Zona, String> descricao;

}

