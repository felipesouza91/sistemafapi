package com.sistemaf.domain.model.definition;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.OffsetDateTime;
import java.util.UUID;

@MappedSuperclass
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class FileReference {

    @Id
    @GeneratedValue(generator = "uudi2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "char(36)")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    @EqualsAndHashCode.Include
    private UUID id;

    private String fileName;

    private String originalFileName;

    private String contentType;

    private Boolean temp = true;

    @CreationTimestamp
    private OffsetDateTime createdAt;

}
