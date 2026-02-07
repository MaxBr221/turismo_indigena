package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Status;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
@Schema(description = "Dados de Editar Agendamento")
public record AgendamentoUpdateDto(@Schema(description = "Data de agendamento", example = "2025-02-10T14:00")
                                   LocalDateTime data,
                                   @Schema(description = "Quantidade de pessoas", example = "2")
                                   int quantPessoas,
                                   @Schema(description = "Status do agendamento", example = "AGENDADO")
                                   Status status) {

}
