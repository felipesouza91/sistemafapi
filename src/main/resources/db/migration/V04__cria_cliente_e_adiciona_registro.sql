create table cliente (
	id bigint(20) primary key auto_increment,
	codigo_service int,
	codigo_sigma int,
	razao_social varchar(255) not null,
	fantazia varchar(255) not null,
	telefone1 varchar(12),
	telefone2 varchar(12),
	dominio varchar(255) not null unique,
	rua varchar(255),
	numero bigint(15),
	complemento varchar(255),
	referencia varchar(255),
	ativo boolean not null,
	bairro_codigo bigint(20) not null,
	codigo_grupo bigint(20) not null,
	FOREIGN KEY (bairro_codigo) REFERENCES bairro(id),
	FOREIGN KEY (codigo_grupo) REFERENCES grupo(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
