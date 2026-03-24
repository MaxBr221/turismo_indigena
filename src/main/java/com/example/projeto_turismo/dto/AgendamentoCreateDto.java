package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Agendamento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;
@Schema(description = "Dados de entrada de Agendamento")
public record AgendamentoCreateDto(
        @NotNull
        @Schema(description = "Data marcada do agendamento", example = "2025-02-10T14:00") LocalDateTime data,
                                   @NotNull
                                   @Schema(description = " Quantidade de pessoas do agendamento", example = "2")int quantPessoas,
                                   @NotNull
                                   @Schema(description = "Id do User responável do agendamento", example = "1") Long user,

                                   @Schema(description = "Id do Guide do agendamento", example = "3") Long guide,
                                   @NotNull
                                   @Schema(description = "Id do Restaurante agendado", example = "2") Long restaurante) {

    public AgendamentoCreateDto(Agendamento agendamento){
        this(agendamento.getData(), agendamento.getQuantPessoas(), agendamento.getUser().getId(), agendamento.getGuide().getId(), agendamento.getRestaurante().getId());
    }
}
