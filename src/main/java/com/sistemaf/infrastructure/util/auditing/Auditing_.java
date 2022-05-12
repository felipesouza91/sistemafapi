package com.sistemaf.infrastructure.util.auditing;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.sistemaf.domain.model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Auditing.class)
public abstract class Auditing_ {

	public static volatile SingularAttribute<Auditing, Usuario> createdBy;
	public static volatile SingularAttribute<Auditing, LocalDateTime> lastModifiedDate;
	public static volatile SingularAttribute<Auditing, Usuario> lastModifiedBy;
	public static volatile SingularAttribute<Auditing, LocalDateTime> creationDate;

}

