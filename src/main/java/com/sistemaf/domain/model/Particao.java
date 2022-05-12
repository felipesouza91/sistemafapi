package com.sistemaf.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sistemaf.domain.model.custompk.ParticaoPk;
import com.sistemaf.infrastructure.util.customserializer.ClienteSerializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "particao")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "particaoPk", scope = Particao.class)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Particao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id", updatable = false, columnDefinition = "auto_increment")
	private Long index;

	@EmbeddedId
	@EqualsAndHashCode.Include
	private ParticaoPk particaoPk;

	@ManyToOne
	@JoinColumn(name = "codigo_cliente", insertable = false, updatable = false)
	@JsonSerialize(using = ClienteSerializer.class)
	private Cliente cliente;

	private Boolean habilitado;

	@Column(name = "tipo_particao")
	@Enumerated(EnumType.STRING)
	private TipoParticao tipoParticao;

	@Column(name = "id", insertable = false, updatable = false)
	private Long idParticao;

	@ManyToOne
	@JoinColumn(name = "codigo_produto")
	private Produto produto;

	@OneToOne
	@JsonManagedReference
	@JoinColumn(name = "cod_conf_cftv")
	private ConfiguracaoCftv configCftv;

	@OneToOne
	@JsonManagedReference
	@JoinColumn(name = "cod_conf_alarme")
	private ConfiguracaoAlarme configAlarme;

	@OneToOne
	@JsonManagedReference
	@JoinColumn(name = "cod_conf_control_acess")
	private ConfiguracaoControleAcesso configControleAcesso;

	public Particao() {	}

	@JsonIgnore
	public ParticaoPk getId() {
		return this.particaoPk;
	}

}
