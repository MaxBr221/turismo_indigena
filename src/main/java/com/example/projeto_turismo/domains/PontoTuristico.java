package com.example.projeto_turismo.domains;

import com.example.projeto_turismo.dto.PontoTuristicoResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pontoturistico")
public class PontoTuristico {
    //modificar coluna de informacoes para "informações adicionais" mais para a frente//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Local local;
    @Column(name = "informacoes", nullable = false)
    private String informacoes;

    public PontoTuristico(PontoTuristicoResponseDto pontoTuristicoResponseDto){
        BeanUtils.copyProperties(pontoTuristicoResponseDto, this);
    }

    public PontoTuristico(String nome, Local local, String informacoes) {
        this.nome = nome;
        this.local = local;
        this.informacoes = informacoes;
    }
}
