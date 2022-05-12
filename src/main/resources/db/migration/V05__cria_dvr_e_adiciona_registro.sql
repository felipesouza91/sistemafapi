create table dvr (
	id bigint(20) primary key auto_increment,
	habilita_verificao boolean not null, 
	porta_http int not null,
	porta_servico int not null,
	fabricante varchar(20) not null,
	modelo_dvr varchar(50) not null,
	ip varchar(20) not null,
	mascara varchar(20) not null,
	gateway varchar(20) not null,
	dns_principal varchar(20) not null,
	dns_alternativo varchar(20),
	numero_serie varchar(50) unique,
	codigo_cliente bigint(20) not null,
	FOREIGN KEY (codigo_cliente) REFERENCES cliente(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
