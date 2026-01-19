create table guide(
    id BIGINT primary key auto_increment,
    nome varchar(100) not null,
    telefone varchar(16) not null,
    login varchar(80) not null unique,
    senha varchar(60) not null);