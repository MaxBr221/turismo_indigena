package com.example.projeto_turismo.domains;

import com.example.projeto_turismo.dto.AvaliacaoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "avaliacao")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer nota;
    private String comentario;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "ponto_id")
    private PontoTuristico pontoTuristico;
    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurantes restaurante;

    public Avaliacao(AvaliacaoDto avaliacaoDto){
        BeanUtils.copyProperties(avaliacaoDto, this);
    }
}
