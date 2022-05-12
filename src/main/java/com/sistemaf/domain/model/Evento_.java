package com.sistemaf.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Evento.class)
public abstract class Evento_ {

	public static volatile SingularAttribute<Evento, String> tipoProtocolo;
	public static volatile SingularAttribute<Evento, String> codigoEvento;
	public static volatile SingularAttribute<Evento, Long> id;
	public static volatile SingularAttribute<Evento, String> descricaoEvento;
	public static volatile SingularAttribute<Evento, EventConfigure> eventConfigure;

}

