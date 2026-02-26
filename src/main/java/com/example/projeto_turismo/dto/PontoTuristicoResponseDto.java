package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Local;
import com.example.projeto_turismo.domains.PontoTuristico;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Saida de dados de PontoTuristico")
public class PontoTuristicoResponseDto {
    @Schema(description = "Id de PontoTuristico", example = "1")
    private Long id;
    @Schema(description = "Nome do PontoTuristico", example = "Praia das Valas")
    private String nome;
    @Schema(description = "Tipo de Local do PontoTuristico", example = "PRAIA")
    private Local local;
    @Schema(description = "Mais informações do PontoTuristico", example = "Vistas linda dos mar com área ventilada")
    private String informacoes;
    private String latitude;
    private String longitude;

    public PontoTuristicoResponseDto(PontoTuristico pontoTuristico){
        BeanUtils.copyProperties(pontoTuristico, this);
    }

}
