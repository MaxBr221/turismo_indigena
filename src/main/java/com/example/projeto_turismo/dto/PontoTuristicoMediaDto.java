package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Local;

public record PontoTuristicoMediaDto(String nome, Local local, String informacoes, String imagem, Double latitude, Double longitude, Double media, String comentario) {
}
