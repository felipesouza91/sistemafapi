create table atendimento (
	id bigint(20) primary key auto_increment,
	descricao_problema text not null,
	descricao_solucao text,
	solicitante varchar(150),
	data_inicio datetime not null,
	data_termino datetime,
	codigo_cliente bigint(20) not null,
	codigo_usuario_inicio bigint(20) not null,
	codigo_usuario_termino bigint(20),
	FOREIGN KEY (codigo_cliente) REFERENCES cliente(id),
	FOREIGN KEY (codigo_usuario_inicio) REFERENCES usuario(id),
	FOREIGN KEY (codigo_usuario_termino) REFERENCES usuario(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;