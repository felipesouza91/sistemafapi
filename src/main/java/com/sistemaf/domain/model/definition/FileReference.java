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
    protected UUID id;

    @Column(name = "file_name")
    protected String fileName;

    @Column(name = "original_file_name")
    protected String originalFileName;

    @Column(name = "content_type")
    protected String contentType;

    protected Boolean temp = true;

    @CreationTimestamp
    @Column(name = "created_at")
    protected OffsetDateTime createdAt;

}
