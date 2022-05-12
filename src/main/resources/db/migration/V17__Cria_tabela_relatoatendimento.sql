create table relato_atendimento (
	id BIGINT(20) primary key auto_increment,
	descricao varchar(70) not null,
	data_criacao datetime not null,
	codigo_usuario BIGINT(20) not null,
	codigo_atendimento BIGINT(20) not null,
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(id),
	FOREIGN KEY (codigo_atendimento) REFERENCES atendimento(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
