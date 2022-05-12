INSERT INTO permissao (id, descricao) values (43, 'RL_CAD_CONTATO');
INSERT INTO permissao (id, descricao) values (44, 'RL_REM_CONTATO');
INSERT INTO permissao (id, descricao) values (45, 'RL_PES_CONTATO');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,43);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,44);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,45);