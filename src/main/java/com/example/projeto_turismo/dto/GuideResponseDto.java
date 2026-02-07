package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Guide;
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
@Schema(description = "Dados de saida de Guide")
public class GuideResponseDto {
    @Schema(description = "Id do Guide", example = "1")
    private Long id;
    @Schema(description = "Nome do Guide", example = "Max")
    private String nome;
    @Schema(description = "Telefone do Guide", example = "8399999900")
    private String telefone;
    @Schema(description = "Descricão do Guide", example = "Sou Max, resido na aldeia são miguel, sou guia turista á 10 anos")
    private String descricao;



    public GuideResponseDto(Guide guide) {
        BeanUtils.copyProperties(guide, this);
    }

}
