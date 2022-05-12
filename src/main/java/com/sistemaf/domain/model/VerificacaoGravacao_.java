package com.sistemaf.domain.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VerificacaoGravacao.class)
public abstract class VerificacaoGravacao_ {

	public static volatile SingularAttribute<VerificacaoGravacao, Integer> qtdGravacao;
	public static volatile SingularAttribute<VerificacaoGravacao, Dvr> dvr;
	public static volatile SingularAttribute<VerificacaoGravacao, Usuario> usuario;
	public static volatile SingularAttribute<VerificacaoGravacao, Long> id;
	public static volatile SingularAttribute<VerificacaoGravacao, String> hd;
	public static volatile SingularAttribute<VerificacaoGravacao, LocalDateTime> dataTeste;
	public static volatile SingularAttribute<VerificacaoGravacao, String> status;

}

