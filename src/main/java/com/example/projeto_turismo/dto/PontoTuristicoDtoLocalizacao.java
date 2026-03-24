package com.example.projeto_turismo.dto;

import jakarta.validation.constraints.NotNull;

public record PontoTuristicoDtoLocalizacao(
        @NotNull
        Double latitude,
        @NotNull
        Double longitude) {
}
