package com.sistemaf.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sistemaf.infrastructure.util.customserializer.UsuarioSerializer;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name="items_stock")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class StockItem {

    @Id
    @GeneratedValue(generator = "uudi2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "char(36)")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    @EqualsAndHashCode.Include
    private UUID id;

    private String serial;

    private Boolean active = true;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Produto produto;

    @CreatedBy
    @ManyToOne
    @JsonSerialize(using= UsuarioSerializer.class)
    @JoinColumn(name="created_by", nullable = false, updatable = false)
    private Usuario createdBy;

    @LastModifiedBy
    @ManyToOne
    @JsonIdentityReference(alwaysAsId=true)
    @JoinColumn(name="updated_by")
    private Usuario updatedBy;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private OffsetDateTime updatedAt;

}
