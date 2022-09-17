package com.sistemaf.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sistemaf.domain.model.custompk.ParticaoPk;
import com.sistemaf.infrastructure.util.customserializer.ClienteSerializer;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "particao")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
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


}
