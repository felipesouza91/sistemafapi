create table if not exists event_configure (
	id bigint(20) primary key auto_increment,
	descricao varchar(255) not null,
	tipo_evento varchar(30) not null,
	prioridade varchar(30) not null,
	envia_monitoramento boolean not null
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists evento (
	id bigint(20) primary key auto_increment,
	descricao_evento varchar(255) not null,
	tipo_protocolo varchar(10) not null,
	codigo_evento varchar(4) not null unique,
	cod_ctrl_conf bigint(20),
	foreign key(cod_ctrl_conf) references event_configure(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
