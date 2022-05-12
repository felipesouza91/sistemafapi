CREATE TABLE permissao (
	id BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE grupo_acesso (
	id BIGINT(20) primary key auto_increment,
	ativo boolean not null,
	descricao VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE table grupo_permissao (
	codigo_grupo BIGINT(20) NOT NULL,
	codigo_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_grupo, codigo_permissao),
	FOREIGN KEY (codigo_grupo) REFERENCES grupo_acesso(id),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO grupo_acesso(descricao, ativo) value ('Administrador', 1);

INSERT INTO permissao (id, descricao) values (1, 'RL_CAD_CLIENTE');
INSERT INTO permissao (id, descricao) values (2, 'RL_PES_CLIENTE');
INSERT INTO permissao (id, descricao) values (3, 'RL_REM_CLIENTE');

INSERT INTO permissao (id, descricao) values (4, 'RL_CAD_GRUPO');
INSERT INTO permissao (id, descricao) values (5, 'RL_REM_GRUPO');
INSERT INTO permissao (id, descricao) values (6, 'RL_PES_GRUPO');

INSERT INTO permissao (id, descricao) values (7, 'RL_CAD_CIDADE');
INSERT INTO permissao (id, descricao) values (8, 'RL_REM_CIDADE');
INSERT INTO permissao (id, descricao) values (9, 'RL_PES_CIDADE');

INSERT INTO permissao (id, descricao) values (10, 'RL_CAD_BAIRRO');
INSERT INTO permissao (id, descricao) values (11, 'RL_REM_BAIRRO');
INSERT INTO permissao (id, descricao) values (12, 'RL_PES_BAIRRO');

INSERT INTO permissao (id, descricao) values (13, 'RL_CAD_DVR');
INSERT INTO permissao (id, descricao) values (14, 'RL_REM_DVR');
INSERT INTO permissao (id, descricao) values (15, 'RL_PES_DVR');

INSERT INTO permissao (id, descricao) values (16, 'RL_CAD_MOTIVO_OS');
INSERT INTO permissao (id, descricao) values (17, 'RL_REM_MOTIVO_OS');
INSERT INTO permissao (id, descricao) values (18, 'RL_PES_MOTIVO_OS');

INSERT INTO permissao (id, descricao) values (19, 'RL_CAD_ORDEM_SERVICO');
INSERT INTO permissao (id, descricao) values (20, 'RL_REM_ORDEM_SERVICO');
INSERT INTO permissao (id, descricao) values (21, 'RL_PES_ORDEM_SERVICO');

INSERT INTO permissao (id, descricao) values (22, 'RL_CAD_FECHAMENTO_ORDEM');
INSERT INTO permissao (id, descricao) values (23, 'RL_REM_ORDEM_FECHAMENTO_ORDEM');
INSERT INTO permissao (id, descricao) values (24, 'RL_PES_ORDEM_FECHAMENTO_ORDEM');

INSERT INTO permissao (id, descricao) values (25, 'RL_CAD_VERIFICA_GRAVACAO');
INSERT INTO permissao (id, descricao) values (26, 'RL_REM_VERIFICA_GRAVACAO');
INSERT INTO permissao (id, descricao) values (27, 'RL_PES_VERIFICA_GRAVACAO');

INSERT INTO permissao (id, descricao) values (28, 'RL_CAD_USUARIO');
INSERT INTO permissao (id, descricao) values (29, 'RL_REM_USUARIO');
INSERT INTO permissao (id, descricao) values (30, 'RL_PES_USUARIO');

INSERT INTO permissao (id, descricao) values (31, 'RL_CAD_ACESSO');
INSERT INTO permissao (id, descricao) values (32, 'RL_REM_ACESSO');
INSERT INTO permissao (id, descricao) values (33, 'RL_PES_ACESSO');

INSERT INTO permissao (id, descricao) values (34, 'RL_CAD_ATENDIMENTO');
INSERT INTO permissao (id, descricao) values (35, 'RL_REM_ATENDIMENTO');
INSERT INTO permissao (id, descricao) values (36, 'RL_PES_ATENDIMENTO');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,1);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,2);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,3);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,4);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,5);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,6);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,7);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,8);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,9);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,10);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,11);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,12);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,13);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,14);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,15);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,16);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,17);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,18);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,19);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,20);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,21);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,22);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,23);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,24);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,25);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,26);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,27);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,28);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,29);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,30);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,31);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,32);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,33);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,34);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,35);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,36);