package com.sistemaf.infrastructure.util.auditing;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.sistemaf.domain.model.Usuario;


@EntityListeners(AuditingEntityListener.class)
public abstract class AuditingHibernate {

	@LastModifiedBy
	@ManyToOne
	@JsonIdentityReference(alwaysAsId=true)
	@JoinColumn(name="modofied_user")
	protected Usuario modifiedBy;
	
	@LastModifiedDate
	@Column(name="modified_date")
	protected LocalDateTime modifiedDate;
	
	public Usuario getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Usuario modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
