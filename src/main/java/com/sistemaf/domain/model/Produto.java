	package com.sistemaf.domain.model;

	import com.sistemaf.infrastructure.util.auditing.Auditing;
	import lombok.*;
	import org.hibernate.envers.AuditOverride;
	import org.hibernate.envers.Audited;
	import org.hibernate.envers.RelationTargetAuditMode;

	import javax.persistence.*;


@Entity
@Table(name="produto")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@AuditOverride(forClass=Auditing.class)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Produto extends Auditing {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(unique=true)
	private String modelo;
	
	private String descricao;
	
	@Column(name="valor_unitario")
	private float valorUnitario;
	
	@ManyToOne
	@JoinColumn(name="codigo_fabricante")
	private Fabricante fabricante;

}
