package com.sistemaf.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Dvr.class)
public abstract class Dvr_ {

	public static volatile SingularAttribute<Dvr, String> numeroSerie;
	public static volatile SingularAttribute<Dvr, String> dnsAlternativo;
	public static volatile SingularAttribute<Dvr, Boolean> somenteCloud;
	public static volatile SingularAttribute<Dvr, String> ip;
	public static volatile SingularAttribute<Dvr, String> modeloDvr;
	public static volatile SingularAttribute<Dvr, Integer> portaHttp;
	public static volatile SingularAttribute<Dvr, Cliente> cliente;
	public static volatile SingularAttribute<Dvr, String> dnsPrincipal;
	public static volatile SingularAttribute<Dvr, Boolean> habilitaVerificao;
	public static volatile SingularAttribute<Dvr, Integer> portaServico;
	public static volatile SingularAttribute<Dvr, Long> id;
	public static volatile SingularAttribute<Dvr, String> fabricante;
	public static volatile SingularAttribute<Dvr, String> mascara;
	public static volatile SingularAttribute<Dvr, String> gateway;

}

