package com.sistemaf.domain.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrdemServico.class)
public abstract class OrdemServico_ {

	public static volatile SingularAttribute<OrdemServico, MotivoOs> motivoOs;
	public static volatile SingularAttribute<OrdemServico, Boolean> fechado;
	public static volatile SingularAttribute<OrdemServico, Cliente> cliente;
	public static volatile SingularAttribute<OrdemServico, Dvr> dvr;
	public static volatile SingularAttribute<OrdemServico, Integer> codigoService;
	public static volatile SingularAttribute<OrdemServico, Integer> codigoSigma;
	public static volatile SingularAttribute<OrdemServico, String> solicitante;
	public static volatile SingularAttribute<OrdemServico, Long> id;
	public static volatile SingularAttribute<OrdemServico, String> prioridadeOs;
	public static volatile SingularAttribute<OrdemServico, String> descricao;
	public static volatile SingularAttribute<OrdemServico, LocalDateTime> dataAbertura;

}

