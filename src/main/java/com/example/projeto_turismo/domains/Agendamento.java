package com.example.projeto_turismo.domains;

import com.example.projeto_turismo.dto.AgendamentoResponseDto;
import com.example.projeto_turismo.exceptions.EventFullException;
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
    //possivel de alteração o notnull de guide, pois vai ter vezes que os turistas vão agendar sem guide//

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

    public Agendamento(AgendamentoResponseDto agendamentoResponseDto){
        BeanUtils.copyProperties(agendamentoResponseDto, this);
    }

    public Agendamento(LocalDateTime data, int quantPessoas, Status status, User user, Guide guide, Restaurantes restaurante) {
        this.data = data;
        if(quantPessoas <= 0){
            throw new EventFullException("É obrigatorio dizer o quantitativo correto de pessoas");
        }
        this.quantPessoas = quantPessoas;
        this.status = status;
        this.user = user;
        this.guide = guide;
        this.restaurante = restaurante;
    }

    public Agendamento(LocalDateTime data, int quantPessoas, Status status, LocalDateTime dataCriacao, User user, Guide guide, Restaurantes restaurante) {
        this.data = data;
        if(quantPessoas <= 0){
            throw new EventFullException("É obrigatorio dizer o quantitativo correto de pessoas");
        }
        this.quantPessoas = quantPessoas;
        this.user = user;
        this.guide = guide;
        this.restaurante = restaurante;
    }
}
