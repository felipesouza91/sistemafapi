package com.sistemaf.domain.model.custompk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Size;

@Embeddable
public class ZonaPk implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(max=3)
	@Column(name="num_zona")
	private String numeroZona;
	
	@Column(name="cod_particao")
	private Long codParticao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroZona() {
		return numeroZona;
	}

	public void setNumeroZona(String numeroZona) {
		this.numeroZona = numeroZona;
	}

	public Long getCodParticao() {
		return codParticao;
	}

	public void setCodParticao(Long codParticao) {
		this.codParticao = codParticao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codParticao == null) ? 0 : codParticao.hashCode());
		result = prime * result + ((numeroZona == null) ? 0 : numeroZona.hashCode());
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
		ZonaPk other = (ZonaPk) obj;
		if (codParticao == null) {
			if (other.codParticao != null)
				return false;
		} else if (!codParticao.equals(other.codParticao))
			return false;
		if (numeroZona == null) {
			if (other.numeroZona != null)
				return false;
		} else if (!numeroZona.equals(other.numeroZona))
			return false;
		return true;
	}
}
