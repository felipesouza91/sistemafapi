package com.sistemaf.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.sistemaf.domain.model.custompk.ParticaoPk;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Particao.class)
public abstract class Particao_ {

	public static volatile SingularAttribute<Particao, ParticaoPk> particaoPk;
	public static volatile SingularAttribute<Particao, Cliente> cliente;
	public static volatile SingularAttribute<Particao, ConfiguracaoAlarme> configAlarme;
	public static volatile SingularAttribute<Particao, Produto> produto;
	public static volatile SingularAttribute<Particao, ConfiguracaoControleAcesso> configControleAcesso;
	public static volatile SingularAttribute<Particao, Long> idParticao;
	public static volatile SingularAttribute<Particao, Long> index;
	public static volatile SingularAttribute<Particao, Boolean> habilitado;
	public static volatile SingularAttribute<Particao, TipoParticao> tipoParticao;
	public static volatile SingularAttribute<Particao, ConfiguracaoCftv> configCftv;

}

