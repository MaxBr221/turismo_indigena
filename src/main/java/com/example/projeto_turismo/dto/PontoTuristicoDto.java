package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Local;
import com.example.projeto_turismo.domains.PontoTuristico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PontoTuristicoDto {
    private Long id;
    private String nome;
    private Local local;
    private String telefone;

    public PontoTuristicoDto(PontoTuristico pontoTuristico){
        BeanUtils.copyProperties(pontoTuristico, this);
    }

}
