create table fechamento_os(
	id bigint(20) primary key auto_increment,
	motivo_fechamento varchar(255) not null,
	data_fechamento datetime not null,
	data_visita datetime not null,
	tecnico varchar(255) not null,
	observacao_servico text not null,
	codigo_os bigint(20) not null,
	FOREIGN KEY (codigo_os) REFERENCES ordem_servico(id)	
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
