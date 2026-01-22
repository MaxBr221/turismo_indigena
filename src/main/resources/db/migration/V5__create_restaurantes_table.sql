create table restaurantes(
    id BIGINT primary key auto_increment,
    nome varchar(80) not null,
    descricao varchar(200) not null,
    tipo varchar(50) not null,
    localizacao varchar(80) not null,
    horariofuncionamento varchar(30) not null,
    telefone varchar(16) not null,
    redesociais varchar(50) not null);