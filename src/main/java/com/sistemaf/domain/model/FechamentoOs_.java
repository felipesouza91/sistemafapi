package com.sistemaf.domain.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FechamentoOs.class)
public abstract class FechamentoOs_ {

	public static volatile SingularAttribute<FechamentoOs, LocalDateTime> dataVisita;
	public static volatile SingularAttribute<FechamentoOs, OrdemServico> os;
	public static volatile SingularAttribute<FechamentoOs, String> motivoFechamento;
	public static volatile SingularAttribute<FechamentoOs, Long> id;
	public static volatile SingularAttribute<FechamentoOs, LocalDateTime> dataFechamento;
	public static volatile SingularAttribute<FechamentoOs, String> tecnico;
	public static volatile SingularAttribute<FechamentoOs, String> observcaoServico;

}

