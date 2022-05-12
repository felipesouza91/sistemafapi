set foreign_key_checks = 0;
set foreign_key_checks = 1;

delete from verificacao_gravacoes;
delete from dvr;
delete from produto;
delete from fabricante;
delete from fechamento_os;
delete from ordem_servico;
delete from cliente_informacao;
delete from cliente;
delete from bairro;
delete from cidade;
delete from usuario;
delete from grupo_permissao;
delete from grupo_acesso;
delete from permissao;
delete from grupo;
delete from motivo_os;

alter table verificacao_gravacoes auto_increment = 1;
alter table dvr auto_increment = 1;
alter table produto auto_increment = 1;
alter table fabricante auto_increment = 1;
alter table fechamento_os auto_increment = 1;
alter table ordem_servico auto_increment = 1;
alter table cliente_informacao auto_increment = 1;
alter table cliente auto_increment = 1;
alter table bairro auto_increment = 1;
alter table cidade auto_increment = 1;
alter table usuario auto_increment = 1;
alter table grupo_permissao auto_increment = 1;
alter table grupo_acesso auto_increment = 1;
alter table permissao auto_increment = 1;
alter table grupo auto_increment = 1;
alter table motivo_os auto_increment = 1;

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

INSERT INTO permissao (id, descricao) values (46, 'RL_CAD_PARTICAO');
INSERT INTO permissao (id, descricao) values (47, 'RL_REM_PARTICAO');
INSERT INTO permissao (id, descricao) values (48, 'RL_PES_PARTICAO');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,46);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,47);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,48);

INSERT INTO permissao (id, descricao) values (43, 'RL_CAD_CONTATO');
INSERT INTO permissao (id, descricao) values (44, 'RL_REM_CONTATO');
INSERT INTO permissao (id, descricao) values (45, 'RL_PES_CONTATO');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,43);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,44);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,45);

INSERT INTO permissao (id, descricao) values (49, 'RL_CAD_CLI_INFO');
INSERT INTO permissao (id, descricao) values (50, 'RL_REM_CLI_INFO');
INSERT INTO permissao (id, descricao) values (51, 'RL_PES_CLI_INFO');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,49);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,50);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,51);

INSERT INTO permissao (id, descricao) values (37, 'RL_CAD_FABRICANTE');
INSERT INTO permissao (id, descricao) values (38, 'RL_REM_FABRICANTE');
INSERT INTO permissao (id, descricao) values (39, 'RL_PES_FABRICANTE');

INSERT INTO permissao (id, descricao) values (40, 'RL_CAD_PRODUTO');
INSERT INTO permissao (id, descricao) values (41, 'RL_REM_PRODUTO');
INSERT INTO permissao (id, descricao) values (42, 'RL_PES_PRODUTO');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,37);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,38);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,39);

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,40);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,41);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) value(1,42);

insert into cidade(nome)  values( 'Rio de Janeiro' );
INSERT INTO bairro (`nome`, `codigo_cidade`) VALUES
('SÃO CRISTÓVÃO', 1),
('BENFICA', 1),
( 'CAJU', 1);

insert into motivo_os(descricao) values( 'PERDA DE VIDEO');

insert into grupo(nome)  values("Padrão");

INSERT INTO usuario (  ativo, nome,apelido, senha, codigo_grupo)
    values (1,'Administrador', 'admin', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.', 1);

INSERT INTO cliente (id,codigo_service, codigo_particao, razao_social, fantazia, telefone1,
    telefone2, dominio, rua, numero, complemento, referencia, ativo, bairro_codigo, codigo_grupo) VALUES (1,
    1234, "1234", "Razão social 01", "Nome Fantasia", "22030195", "25165842", "meudominio.com.br", "Rua da empresa",
     1234 , "compelmento 1","ao lado da empresa", true, 1, 1  );

INSERT INTO ordem_servico(id, codigo_service, codigo_sigma, codigo_motivo_os, descricao, prioridade_os, solicitante, codigo_cliente, data_abertura)
    VALUES (1, 1234,1234, 1, "DESCRIÇÃO DA ORDEM DE SERVICO", "ALTA", "FELIPE SOUZA", 1,LOCALTIMESTAMP);

INSERT INTO fechamento_os(id, motivo_fechamento, data_fechamento, data_visita, tecnico, observacao_servico, codigo_os)
    VALUES( 1, "CONCLUIDO", LOCALTIMESTAMP, LOCALTIMESTAMP, "UM TÉCNICO", "FOI REALIZADO O SERVIÇO CONFORME SOLICITADO", 1);

INSERT INTO fabricante(id ,	descricao_fab, create_user, create_date )
    VALUES (1, "FAB 01", 1, LOCALTIMESTAMP);

INSERT INTO produto( id, codigo_fabricante , modelo , descricao , valor_unitario , create_user, create_date )
    VALUES (1, 1, "any model", "any description", 100.00, 1, LOCALTIMESTAMP ) ;

INSERT INTO dvr (id,habilita_verificao,	porta_http,porta_servico,fabricante,modelo_dvr,ip,mascara,gateway,dns_principal,dns_alternativo ,numero_serie, codigo_cliente)
    VALUES (1, 1, 10000, 4550, "INTEBRAS", "MHDX 1108", "192.168.0.1", "255.255.255.0", "192.168.0.1", "8.8.8.8", "8.8.4.4", "Asdasdasd", 1);

INSERT INTO verificacao_gravacoes (id, status,hd ,qtd_gravacao ,data_teste, codigo_dvr, codigo_usuario)
    VALUES (1, "ONLINE", "100GB", 250, LOCALTIMESTAMP, 1, 1);