package com.sistemaf.domain.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sistemaf.domain.model.custompk.ZonaPk;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="zona")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "zonaPk", scope=Particao.class)
@Getter
@Setter
public class Zona {
	
	@EmbeddedId
	private ZonaPk id;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="observacao")
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name="cod_particao",insertable=false, updatable=false, referencedColumnName="id")
	@JsonIdentityReference(alwaysAsId=true)
	private Particao particao;
	
	@ManyToOne
	@JoinColumn(name="cod_produto")
	private Produto produto;

	@Column(name="id",insertable=false,updatable=false)
	private Long idZona;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id.getNumeroZona() == null) ? 0 : id.getNumeroZona().hashCode());
		result = prime * result + ((id.getCodParticao() == null) ? 0 : id.getCodParticao().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zona other = (Zona) obj;
		if (id.getCodParticao() == null) {
			if (other.getId().getCodParticao() != null)
				return false;
		} else if (!id.getCodParticao().equals(other.getId().getCodParticao()))
			return false;
		if (id.getNumeroZona() == null) {
			if (other.getId().getNumeroZona() != null)
				return false;
		} else if (!id.getNumeroZona().equals(other.getId().getNumeroZona()))
			return false;
		return true;
	}
	
}
