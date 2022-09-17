package com.sistemaf.domain.model;

import com.sistemaf.domain.model.definition.ConfigParticao;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="config_part_alarme")
@Getter
@Setter
public class ConfiguracaoAlarme extends ConfigParticao{

}
