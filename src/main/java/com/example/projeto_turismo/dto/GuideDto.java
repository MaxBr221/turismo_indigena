package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Guide;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GuideDto {
    private Long id;
    private String nome;
    private String telefone;
    private String descricao;



    public GuideDto(Guide guide) {
        BeanUtils.copyProperties(guide, this);
    }

}
