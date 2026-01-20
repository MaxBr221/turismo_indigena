package com.example.projeto_turismo.domains;

import com.example.projeto_turismo.dto.PontoTuristicoDto;
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
@Table(name = "pontoTuristico")
public class PontoTuristico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Local local;
    @Column(name = "telefone", nullable = false)
    private String telefone;

    public PontoTuristico(PontoTuristicoDto pontoTuristicoDto){
        BeanUtils.copyProperties(pontoTuristicoDto, this);
    }

    public PontoTuristico(String nome, Local local, String telefone) {
        this.nome = nome;
        this.local = local;
        this.telefone = telefone;
    }
}
