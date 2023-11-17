CREATE TABLE client_files (
    id char(36) primary key not null,
    file_name varchar(255) not null,
    original_file_name varchar(255),
    content_type varchar(20),
    temp boolean not null,
    client_id bigint(20) not null,
    created_at datetime not null,
    FOREIGN KEY (client_id) REFERENCES cliente(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;