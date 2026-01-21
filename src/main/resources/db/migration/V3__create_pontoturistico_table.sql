create table pontoturistico(
    id BIGINT primary key auto_increment,
    nome varchar(70) not null,
    local ENUM('RIO', 'PRAIA', 'CENTRO', 'ALDEIA') not null,
    telefone varchar(16) not null);