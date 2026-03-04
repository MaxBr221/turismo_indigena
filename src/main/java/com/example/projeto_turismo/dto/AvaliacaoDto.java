package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Avaliacao;
//colocar anotations de valid e not null futuramente
public record AvaliacaoDto(Integer nota, String comentario, Long idUser, Long idPonto, Long idRestaurante) {


    public AvaliacaoDto(Avaliacao avaliacao){
        this(avaliacao.getNota(),
        avaliacao.getComentario(),
        avaliacao.getUser().getId(),
        avaliacao.getPontoTuristico().getId(),
        avaliacao.getRestaurante().getId());
    }
}
