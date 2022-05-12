ALTER TABLE particao
	DROP COLUMN `codigo`,
	DROP COLUMN `monitora`;

ALTER TABLE particao
	ADD COLUMN `num_particao` varchar(3),
	ADD COLUMN `habilitado` boolean,
	ADD COLUMN `tipo_particao` varchar(20),
	ADD COLUMN `cod_conf_cftv` bigint(20),
	ADD COLUMN `cod_conf_alarme` bigint(20),
	ADD COLUMN `cod_conf_control_acess` bigint(20);

ALTER TABLE particao
	ADD CONSTRAINT particao_pk
		UNIQUE (num_particao, codigo_cliente);

create table if not exists config_part_cftv (
	id bigint(20) primary key auto_increment,
	cod_particao bigint(20),
	habilita_teste boolean,
	tempo_teste bigint(20),
	porta_http int not null,
	porta_servico int not null,
	ip varchar(20) not null,
	mascara varchar(20) not null,
	gateway varchar(20) not null,
	dns_principal varchar(20) not null,
	dns_alternativo varchar(20),
	numero_serie varchar(50) unique,
	somente_cloud boolean,
	FOREIGN KEY (cod_particao) REFERENCES particao(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
create table if not exists config_part_alarme (
	id bigint(20) primary key auto_increment,
	cod_particao bigint(20),
	habilita_teste boolean,
	tempo_teste bigint(20),
	FOREIGN KEY (cod_particao) REFERENCES particao(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists config_part_controle_acesso (
	id bigint(20) primary key auto_increment,
	cod_particao bigint(20),
	habilita_teste boolean,
	tempo_teste bigint(20),
	FOREIGN KEY (cod_particao) REFERENCES particao(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table cliente CHANGE codigo_sigma codigo_particao varchar(4) unique not null;

alter table particao add constraint fk_cod_conf_cft FOREIGN KEY (cod_conf_cftv) REFERENCES config_part_cftv(id);
alter table particao add constraint fk_cod_conf_alarme FOREIGN KEY (cod_conf_alarme) REFERENCES config_part_alarme(id);
alter table particao add constraint fk_cod_conf_acesso FOREIGN KEY (cod_conf_control_acess) REFERENCES config_part_controle_acesso(id);

INSERT INTO permissao (id, descricao) values (46, 'RL_CAD_PARTICAO');
INSERT INTO permissao (id, descricao) values (47, 'RL_REM_PARTICAO');
INSERT INTO permissao (id, descricao) values (48, 'RL_PES_PARTICAO');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,46);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,47);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,48);
