package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Avaliacao;
import com.example.projeto_turismo.exceptions.EventFullException;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//o postman mapea de acordo com o nome do dto
public record AvaliacaoDto(
        @NotNull
        @Min(0)
        @Max(10)
        Integer nota,
        @NotBlank
        String comentario,
        @NotNull
        Long idPonto,
        @NotNull
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
