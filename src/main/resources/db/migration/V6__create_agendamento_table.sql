create table agendamento(
    idagendamento BIGINT primary key auto_increment,
    data_agendamento DATETIME not null,
    quantpessoas int not null,
    status varchar(50) not null,
    datacriacao DATETIME not null);

