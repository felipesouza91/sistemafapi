package com.sistemaf.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
@Getter
@Setter
public class Endereco {

	private String rua;
	
	private Long numero;
	
	private String complemento;
	
	private String referencia;

	@ManyToOne
	@JoinColumn( name="bairro_codigo" )
	private Bairro bairro;

}
