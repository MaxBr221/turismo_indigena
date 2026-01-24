alter table agendamento
add constraint fk_agendamento_users
foreign key(id_users) references users(id);

alter table agendamento
add constraint fk_agendamento_guide
foreign key(id_guide) references guide(id);

alter table agendamento
add constraint fk_agendamento_restaurantes
foreign key(id_restaurantes) references restaurantes(id);