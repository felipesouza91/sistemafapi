create table if not exists cliente_informacao (
	id BIGINT(20) primary key auto_increment,
	descricao text not null,
	create_date datetime not null,
	modified_date datetime not null,
	create_user BIGINT(20) not null,
	modofied_user BIGINT(20) not null,
	codigo_cliente BIGINT(20) not null,
	FOREIGN KEY (create_user) REFERENCES usuario(id),
	FOREIGN KEY (modofied_user) REFERENCES usuario(id),
	FOREIGN KEY (codigo_cliente) REFERENCES cliente(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO permissao (id, descricao) values (49, 'RL_CAD_CLI_INFO');
INSERT INTO permissao (id, descricao) values (50, 'RL_REM_CLI_INFO');
INSERT INTO permissao (id, descricao) values (51, 'RL_PES_CLI_INFO');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,49);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,50);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,51);