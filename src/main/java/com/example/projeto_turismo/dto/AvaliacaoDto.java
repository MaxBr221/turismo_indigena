package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Avaliacao;
//colocar anotations de valid e not null futuramente
//o postman mapea de acordo com o nome do dto
public record AvaliacaoDto(Integer nota, String comentario, Long idPonto, Long idRestaurante) {

    public AvaliacaoDto(Avaliacao avaliacao){
        this(avaliacao.getNota(),
        avaliacao.getComentario(),
        avaliacao.getPontoTuristico().getId(),
        avaliacao.getRestaurante().getId());
    }
}
