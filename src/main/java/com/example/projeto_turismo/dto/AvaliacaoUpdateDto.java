package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Avaliacao;

public record AvaliacaoUpdateDto(Integer nota, String comentario, Long pontoId, Long restauranteId) {

    public AvaliacaoUpdateDto(Avaliacao avaliacao){
        this(avaliacao.getNota(), avaliacao.getComentario(), avaliacao.getPontoTuristico().getId(), avaliacao.getRestaurante().getId());
    }
}
