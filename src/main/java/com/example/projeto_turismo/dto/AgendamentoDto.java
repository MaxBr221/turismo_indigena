package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Agendamento;
import com.example.projeto_turismo.domains.Status;
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
public class AgendamentoDto {
    private Long id;
    private LocalDateTime data;
    private int quantPessoas;
    private Status status;
    private LocalDateTime dataCriacao;

    public AgendamentoDto(Agendamento agendamento){
        BeanUtils.copyProperties(agendamento, this);
    }

}
