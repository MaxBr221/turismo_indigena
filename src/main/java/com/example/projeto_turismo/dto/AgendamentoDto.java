package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Long user;
    private Long guide;
    private Long restaurante;

    //alterar para o user não poder definir o tipo do Enum(Status), nem LocalDateTime de dataCriacao//

    public AgendamentoDto(Agendamento agendamento){
        BeanUtils.copyProperties(agendamento, this);
    }

}
