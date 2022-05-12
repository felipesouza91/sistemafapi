create table if not exists zona (
	id bigint(20) auto_increment,
	num_zona varchar(3),
	cod_produto bigint(20),
	cod_particao bigint(20),
	descricao varchar(255),
	observacao varchar(255),
	primary key (id, num_zona, cod_particao),
	foreign key(cod_particao) references particao(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO permissao (id, descricao) values (52, 'RL_CAD_ZONE');
INSERT INTO permissao (id, descricao) values (53, 'RL_REM_ZONE');
INSERT INTO permissao (id, descricao) values (54, 'RL_PES_ZONE');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,52);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,53);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,54);