CREATE TABLE usuario (
	id BIGINT(20) PRIMARY KEY auto_increment,
	ativo boolean not null,
	nome VARCHAR(50) NOT NULL,
	apelido VARCHAR(50) NOT NULL UNIQUE,
	senha VARCHAR(150) NOT NULL,
	codigo_grupo BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_grupo) REFERENCES grupo_acesso(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
INSERT INTO usuario (  ativo, nome,apelido, senha, codigo_grupo) values (1,'Administrador', 'admin', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.', 1);