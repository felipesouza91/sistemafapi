create table grupo (
	id BIGINT(20) primary key auto_increment,
	nome varchar(70) not null unique
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into grupo(nome)  values("Padrão");