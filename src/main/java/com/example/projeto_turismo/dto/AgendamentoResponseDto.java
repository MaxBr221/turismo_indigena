package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dados de saida de Agendamento")
public class AgendamentoResponseDto {
    @Schema(description = "Id do agendamento", example = "2")
    private Long id;
    @Schema(description = "Data marcada do agendamento", example = "2025-02-10T14:00")
    private LocalDateTime data;
    @Schema(description = "Quantidade de pessoas")
    private int quantPessoas;
    @Schema(description = "Status do agendamento", example = "5")
    private Status status;
    @Schema(description = "Data de criação do agendamento", example = "2025-01-10T13:00")
    private LocalDateTime dataCriacao;
    @Schema(description = "Id do User responsável pelo agendamento", example = "1")
    private Long user;
    @Schema(description = "Id do Guide responsável pelo agendamento", example = "3")
    private Long guide;
    @Schema(description = "Id do Restaurante agendado", example = "2")
    private Long restaurante;

    public AgendamentoResponseDto(Agendamento agendamento){
        BeanUtils.copyProperties(agendamento, this);
    }

}
