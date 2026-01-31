package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.*;
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
public class AgendamentoResponseDto {
    private Long id;
    private LocalDateTime data;
    private int quantPessoas;
    private Status status;
    private LocalDateTime dataCriacao;
    private Long user;
    private Long guide;
    private Long restaurante;

    //fazer uma logica para não poder adicionar agendamento com data já marcada(mesmo dia e hora)//
    public AgendamentoResponseDto(Agendamento agendamento){
        BeanUtils.copyProperties(agendamento, this);
    }

}
