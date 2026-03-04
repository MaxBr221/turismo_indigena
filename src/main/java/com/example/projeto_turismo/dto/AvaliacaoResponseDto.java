package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Avaliacao;

import java.time.LocalDateTime;

public record AvaliacaoResponseDto(Integer nota, String comentario, Long idUser, Long idPonto, Long idRestaurante, LocalDateTime dataAvaliacao) {


    public AvaliacaoResponseDto(Avaliacao avaliacao){
        this(avaliacao.getNota(),
        avaliacao.getComentario(),
        avaliacao.getUser().getId(),
        avaliacao.getPontoTuristico().getId(),
        avaliacao.getRestaurante().getId(),
        avaliacao.getDataAvaliacao());
    }
}
