CREATE TABLE particao (
	id bigint(20) primary key auto_increment,
	codigo varchar(3) not null,
	monitora boolean,
	codigo_cliente bigint(20),
	FOREIGN KEY (codigo_cliente) REFERENCES cliente(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;