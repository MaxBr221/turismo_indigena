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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Local local;
    @Column(name = "telefone", nullable = false)
    private String telefone;

    public PontoTuristico(PontoTuristicoResponseDto pontoTuristicoResponseDto){
        BeanUtils.copyProperties(pontoTuristicoResponseDto, this);
    }

    public PontoTuristico(String nome, Local local, String telefone) {
        this.nome = nome;
        this.local = local;
        this.telefone = telefone;
    }
}
