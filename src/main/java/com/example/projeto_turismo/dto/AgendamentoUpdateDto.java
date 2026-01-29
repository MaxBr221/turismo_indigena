package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Status;

import java.time.LocalDateTime;

public record AgendamentoUpdateDto(LocalDateTime data, int quantPessoas, Status status) {

}
