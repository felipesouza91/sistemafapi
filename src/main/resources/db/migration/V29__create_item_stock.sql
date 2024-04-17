UPDATE permissao SET descricao='RL_CAD_STOCK_ITEM' where id= 55;
UPDATE permissao SET descricao='RL_REM_STOCK_ITEM' where id= 56;
UPDATE permissao SET descricao='RL_PES_STOCK_ITEM' where id= 57;


CREATE TABLE if NOT EXISTS items_stock (
    id CHAR(36) DEFAULT (uuid()) PRIMARY KEY NOT NULL,
    serial varchar(255) NOT NULL UNIQUE,
    product_id BIGINT(20) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at datetime NOT NULL,
    updated_at datetime NOT NULL,
    created_by BIGINT(20) NOT NULL,
    updated_by BIGINT(20),
    CONSTRAINT fk_product_id_item_stock FOREIGN KEY (product_id) REFERENCES produto(id),
    CONSTRAINT fk_create_by_items_stock FOREIGN KEY (created_by) REFERENCES usuario(id),
    CONSTRAINT fk_updated_by_items_stock FOREIGN KEY (created_by) REFERENCES usuario(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;