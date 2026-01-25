package com.example.projeto_turismo.domains;

import com.example.projeto_turismo.dto.AgendamentoDto;
import jakarta.persistence.*;
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
@Entity
@Table(name = "agendamento")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idagendamento")
    private Long id;
    @Column(name = "data_agendamento", nullable = false, unique = true)
    private LocalDateTime data;
    @Column(name = "quantpessoas", nullable = false)
    private int quantPessoas;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
    @Column(name = "datacriacao", nullable = false)
    private LocalDateTime dataCriacao;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_users", nullable = false)
    private User user;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_guide", nullable = false)
    private Guide guide;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_restaurantes", nullable = false)
    private Restaurantes restaurante;

    public Agendamento(AgendamentoDto agendamentoDto){
        BeanUtils.copyProperties(agendamentoDto, this);
    }

    public Agendamento(LocalDateTime data, int quantPessoas, Status status, User user, Guide guide, Restaurantes restaurante) {
        this.data = data;
        this.quantPessoas = quantPessoas;
        this.status = status;
        this.user = user;
        this.guide = guide;
        this.restaurante = restaurante;
    }
}
