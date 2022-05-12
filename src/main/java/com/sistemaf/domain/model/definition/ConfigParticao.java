package com.sistemaf.domain.model.definition;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sistemaf.domain.model.Particao;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class ConfigParticao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name="habilita_teste")
	private Boolean habilitaTeste; 
	
	@Column(name="tempo_teste") 
	private Long tempoTeste;
	
	@OneToOne
	@JoinColumn(name="codigo_particao", referencedColumnName="id")
	@JsonBackReference
	private Particao particao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getHabilitaTeste() {
		return habilitaTeste;
	}

	public void setHabilitaTeste(Boolean habilitaTeste) {
		this.habilitaTeste = habilitaTeste;
	}

	public Long getTempoTeste() {
		return tempoTeste;
	}

	public void setTempoTeste(Long tempoTeste) {
		this.tempoTeste = tempoTeste;
	}

	public Particao getParticao() {
		return particao;
	}

	public void setParticao(Particao particao) {
		this.particao = particao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ConfigParticao other = (ConfigParticao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
