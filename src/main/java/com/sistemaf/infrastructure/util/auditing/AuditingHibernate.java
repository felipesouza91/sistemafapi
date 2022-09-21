package com.sistemaf.infrastructure.util.auditing;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.sistemaf.domain.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.OffsetDateTime;


@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AuditingHibernate {

	@LastModifiedBy
	@ManyToOne
	@JsonIdentityReference(alwaysAsId=true)
	@JoinColumn(name="modofied_user")
	protected Usuario modifiedBy;
	
	@UpdateTimestamp
	@Column(name="modified_date")
	protected OffsetDateTime modifiedDate;

}
