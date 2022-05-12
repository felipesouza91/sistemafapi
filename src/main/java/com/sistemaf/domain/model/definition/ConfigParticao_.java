package com.sistemaf.domain.model.definition;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.sistemaf.domain.model.Particao;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConfigParticao.class)
public abstract class ConfigParticao_ {

	public static volatile SingularAttribute<ConfigParticao, Boolean> habilitaTeste;
	public static volatile SingularAttribute<ConfigParticao, Long> tempoTeste;
	public static volatile SingularAttribute<ConfigParticao, Particao> particao;
	public static volatile SingularAttribute<ConfigParticao, Long> id;

}

