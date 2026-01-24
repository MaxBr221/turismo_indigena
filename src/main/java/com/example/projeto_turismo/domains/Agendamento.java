package com.example.projeto_turismo.domains;

import com.example.projeto_turismo.dto.AgendamentoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agendamento")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data", nullable = false)
    private LocalDateTime data;
    @Column(name = "quantpessoas", nullable = false)
    private int quantPessoas;
    @Column(name = "status", nullable = false)
    private Status status;
    @Column(name = "datacriacao", nullable = false)
    private LocalDateTime dataCriacao;

    public Agendamento(AgendamentoDto agendamentoDto){
        BeanUtils.copyProperties(agendamentoDto, this);
    }

    public Agendamento(LocalDateTime data, int quantPessoas, Status status, LocalDateTime dataCriacao) {
        this.data = data;
        this.quantPessoas = quantPessoas;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }
}
