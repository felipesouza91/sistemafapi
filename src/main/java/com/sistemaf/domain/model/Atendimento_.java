package com.sistemaf.domain.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Atendimento.class)
public abstract class Atendimento_ {

	public static volatile SingularAttribute<Atendimento, Cliente> cliente;
	public static volatile SingularAttribute<Atendimento, Usuario> usuarioInicio;
	public static volatile SingularAttribute<Atendimento, String> descricaoProblema;
	public static volatile SingularAttribute<Atendimento, String> descricaoSolucao;
	public static volatile SingularAttribute<Atendimento, String> solicitante;
	public static volatile SingularAttribute<Atendimento, LocalDateTime> dataTermino;
	public static volatile SingularAttribute<Atendimento, Usuario> usuarioTermino;
	public static volatile SingularAttribute<Atendimento, Long> id;
	public static volatile SingularAttribute<Atendimento, LocalDateTime> dataInicio;
	public static volatile ListAttribute<Atendimento, RelatoAtendimento> relatos;

}

