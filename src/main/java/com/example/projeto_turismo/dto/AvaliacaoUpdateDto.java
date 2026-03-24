package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Avaliacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoUpdateDto(
        @NotNull
        Integer nota,
        @NotBlank
        String comentario,
        @NotNull
        Long pontoId,
        @NotNull
        Long restauranteId) {

    public AvaliacaoUpdateDto(Avaliacao avaliacao){
        this(avaliacao.getNota(), avaliacao.getComentario(), avaliacao.getPontoTuristico().getId(), avaliacao.getRestaurante().getId());
    }
}
