package com.sistemaf.domain.model.custompk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ParticaoPk implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "num_particao")
	private String numeroParticao;

	@Column(name = "codigo_cliente")
	private Long clienteId;
	
	public ParticaoPk() {	}

	public String getNumeroParticao() {
		return numeroParticao;
	}

	public void setNumeroParticao(String numeroParticao) {
		this.numeroParticao = numeroParticao;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clienteId == null) ? 0 : clienteId.hashCode());
		result = prime * result + ((numeroParticao == null) ? 0 : numeroParticao.hashCode());
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
		ParticaoPk other = (ParticaoPk) obj;
		if (clienteId == null) {
			if (other.clienteId != null)
				return false;
		} else if (!clienteId.equals(other.clienteId))
			return false;
		if (numeroParticao == null) {
			if (other.numeroParticao != null)
				return false;
		} else if (!numeroParticao.equals(other.numeroParticao))
			return false;
		return true;
	}

}
