package com.sistemaf.infrastructure.util.auditing;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sistemaf.domain.model.Usuario;
import com.sistemaf.infrastructure.util.customserializer.UsuarioSerializer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditing {

	@CreatedBy
	@ManyToOne
	@JsonSerialize(using=UsuarioSerializer.class)
	@JoinColumn(name="create_user", nullable = false, updatable = false)
	@NotAudited
	protected Usuario createdBy;
	
	@CreationTimestamp
	@Column(name="create_date", nullable = false, updatable = false)
	@NotAudited
	protected OffsetDateTime creationDate;
	
	@LastModifiedBy
	@ManyToOne
	@JsonIdentityReference(alwaysAsId=true)
	@JoinColumn(name="modofied_user")
	protected Usuario lastModifiedBy;
	
	@UpdateTimestamp
	@Column(name="modified_date")
	protected OffsetDateTime lastModifiedDate;

}