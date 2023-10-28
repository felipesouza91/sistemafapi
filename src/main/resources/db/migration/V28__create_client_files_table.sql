CREATE TABLE client_files (
    id char(36) primary key not null,
    fileName varchar(255) not null,
    originalFileName varchar(255),
    contentType varchar(20),
    temp boolean not null,
    client_id bigint(20) not null,
    FOREIGN KEY (client_id) REFERENCES cliente(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;