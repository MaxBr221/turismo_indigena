package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Guide;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados de entrada de Guide")

public record GuideCreateDto(@Schema(description = "Nome do guide", example = "max")
                            @NotBlank
                             String nome,
                             @Schema(description = "Telefone do guide", example = "83 991812222")
                             @NotBlank
                             String telefone,
                             @Schema(description = "Descricão do guide", example = "Sou Max, resido na aldeia são miguel, sou guia turista á 10 anos")
                             @NotBlank
                             String descricao) {

    public GuideCreateDto(Guide guide){
        this(guide.getNome(), guide.getTelefone(), guide.getDescricao());
    }
}
