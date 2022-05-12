package com.sistemaf.infrastructure.util.auditing;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sistemaf.domain.model.Usuario;
import com.sistemaf.infrastructure.util.customserializer.UsuarioSerializer;

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
	
	@CreatedDate
	@Column(name="create_date", nullable = false, updatable = false)
	@NotAudited
	protected LocalDateTime creationDate;
	
	@LastModifiedBy
	@ManyToOne
	@JsonIdentityReference(alwaysAsId=true)
	@JoinColumn(name="modofied_user")
	protected Usuario lastModifiedBy;
	
	@LastModifiedDate
	@Column(name="modified_date")
	protected LocalDateTime lastModifiedDate;

}