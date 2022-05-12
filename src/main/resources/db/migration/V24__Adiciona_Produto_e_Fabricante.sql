create table if not exists fabricante (
		id BIGINT(20) primary key auto_increment,
		descricao_fab varchar(50) not null unique,
		create_user BIGINT(20) not null,
		create_date datetime,
		modified_date datetime,
		modofied_user BIGINT(20),
		FOREIGN KEY (modofied_user) REFERENCES usuario(id),
		FOREIGN KEY (create_user) REFERENCES usuario(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists fabricante_aud (
		id BIGINT(20) not null,
		modified_date datetime,
		modofied_user BIGINT(20),
		descricao_fab varchar(50),
		REV integer not null,
    	REVTYPE tinyint,
    	primary key (id, REV),
    	FOREIGN KEY (modofied_user) REFERENCES usuario(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists revinfo (
    REV integer primary key auto_increment,
    REVTSTMP bigint
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists produto (
		id BIGINT(20) primary key auto_increment,
		codigo_fabricante BIGINT(20),
		modelo varchar(255) not null unique,
		descricao varchar(255),
		valor_unitario float not null,
		create_user BIGINT(20),
		create_date datetime,
		modified_date datetime,
		modofied_user BIGINT(20),
		FOREIGN KEY (create_user) REFERENCES usuario(id),
		FOREIGN KEY (modofied_user) REFERENCES usuario(id),
		FOREIGN KEY (codigo_fabricante) REFERENCES fabricante(id)	
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists produto_aud (
		id BIGINT(20) not null,
		codigo_fabricante BIGINT(20),
		modelo varchar(255) ,
		descricao varchar(255),
		valor_unitario float ,
		modified_date datetime,
		modofied_user BIGINT(20),
		REV integer not null,
    	REVTYPE tinyint,
    	primary key (id, REV),
		FOREIGN KEY (modofied_user) REFERENCES usuario(id),
		FOREIGN KEY (codigo_fabricante) REFERENCES fabricante(id)	
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table particao add codigo_produto bigint(20);

alter table fabricante_aud
   add constraint fk_fab_aud
   foreign key (REV)
   references revinfo(REV);   
   
alter table produto_aud
   add constraint fk_prod_aud
   foreign key (REV)
   references revinfo(REV);   

alter table particao add CONSTRAINT codigo_produto
FOREIGN KEY (codigo_produto) REFERENCES produto(id);

alter table zona add CONSTRAINT fk_codigo_produto
foreign key(cod_produto) references produto(id);

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