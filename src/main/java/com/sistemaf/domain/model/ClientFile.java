package com.sistemaf.domain.model;

import com.sistemaf.domain.model.definition.FileReference;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "client_files")
public class ClientFile extends FileReference {

    @ManyToOne()
    @JoinColumn(name = "client_id")
    private Cliente client;
}
