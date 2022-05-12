CREATE TABLE contato(
	id bigint(20) primary key auto_increment,
	nome varchar(100) unique not null,
	telefone varchar(12),
	celular varchar(12),
	senha varchar(255),
	contra_senha varchar(255),
	possicao varchar(3),
	usuario boolean,
	codigo_particao bigint(20),
	codigo_funcao bigint(20),
	FOREIGN KEY (codigo_particao) REFERENCES particao(id),
	FOREIGN KEY (codigo_funcao) REFERENCES funcao(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;