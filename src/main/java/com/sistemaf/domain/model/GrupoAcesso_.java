package com.sistemaf.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GrupoAcesso.class)
public abstract class GrupoAcesso_ {

	public static volatile ListAttribute<GrupoAcesso, Permissao> permissoes;
	public static volatile SingularAttribute<GrupoAcesso, Boolean> ativo;
	public static volatile SingularAttribute<GrupoAcesso, Long> id;
	public static volatile SingularAttribute<GrupoAcesso, String> descricao;

}

