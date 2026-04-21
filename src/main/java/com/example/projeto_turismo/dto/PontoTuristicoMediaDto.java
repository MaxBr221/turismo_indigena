package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Local;
import com.example.projeto_turismo.domains.PontoTuristico;

public record PontoTuristicoMediaDto(String nome, Local local, String informacoes, String imagem, Double latitude, Double longitude, Double media, String comentario, int avaliacoes) {
    public PontoTuristicoMediaDto (PontoTuristico pontoTuristico){
        this(pontoTuristico.getNome(),
                pontoTuristico.getLocal(),
                pontoTuristico.getInformacoes(),
                pontoTuristico.getImagem(),
                pontoTuristico.getLatitude(),
                pontoTuristico.getLongitude(),
                pontoTuristico.getMedia(),
                pontoTuristico.getComentario(),
                pontoTuristico.getAvaliacoes());

    }
}
