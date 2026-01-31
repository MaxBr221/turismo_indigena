package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Agendamento;


import java.time.LocalDateTime;

public record AgendamentoCreateDto(LocalDateTime data, int quantPessoas, Long user, Long guide, Long restaurante) {

    public AgendamentoCreateDto(Agendamento agendamento){
        this(agendamento.getData(), agendamento.getQuantPessoas(), agendamento.getUser().getId(), agendamento.getGuide().getId(), agendamento.getRestaurante().getId());
    }
}
