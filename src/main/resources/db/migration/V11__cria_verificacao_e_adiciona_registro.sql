create table verificacao_gravacoes(
	id bigint(20) primary key auto_increment,
	status varchar(8) not null,
	hd varchar(30) not null,
	qtd_gravacao int not null,
	data_teste datetime not null	,
	codigo_dvr bigint(20) not null,
	codigo_usuario bigint(20) not null,
	FOREIGN KEY (codigo_dvr) REFERENCES dvr(id),
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;