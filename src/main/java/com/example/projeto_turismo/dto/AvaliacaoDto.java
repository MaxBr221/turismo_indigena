package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Avaliacao;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoDto(
        @Min(0)
        @Max(10)
        @JsonProperty("notaEmNumero")
        Integer nota,
        @JsonProperty("comentarioDigitado")
        String comentario,
        Long idPonto,
        @JsonProperty("id")
        Long idRestaurante) {


    public AvaliacaoDto{
        if (nota < 0 || nota > 10){
            throw new EventFullException("A nota não pode ser menor que 0 e nem maior que 10");
        }
    }

    public AvaliacaoDto(Avaliacao avaliacao){
        this(avaliacao.getNota(),
        avaliacao.getComentario(),
        avaliacao.getPontoTuristico().getId(),
        avaliacao.getRestaurante().getId());
    }
}
