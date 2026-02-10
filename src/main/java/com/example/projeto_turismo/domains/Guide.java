package com.example.projeto_turismo.domains;

import com.example.projeto_turismo.dto.GuideResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "guide")
public class Guide{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "telefone", nullable = false)
    private String telefone;
    @Column(name = "descricao")
    private String descricao;


    public Guide(GuideResponseDto guideResponseDto) {
        BeanUtils.copyProperties(guideResponseDto, this);
    }
}
