create table ordem_servico(
	id bigint(20) primary key auto_increment,
	codigo_service int,
	codigo_sigma int,
	codigo_motivo_os bigint(20) not null,
	descricao text,
	prioridade_os varchar(11) not null,
	solicitante varchar(255) not null,
	codigo_cliente bigint(20) not null,
	codigo_dvr bigint(20),
	data_abertura datetime not null,
	fechado boolean,
	FOREIGN KEY (codigo_motivo_os) REFERENCES motivo_os(id),
	FOREIGN KEY (codigo_cliente) REFERENCES cliente(id),
	FOREIGN KEY (codigo_dvr) REFERENCES dvr(id) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
