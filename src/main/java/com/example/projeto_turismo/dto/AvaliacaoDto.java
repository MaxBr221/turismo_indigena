package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Avaliacao;
import com.example.projeto_turismo.exceptions.EventFullException;

//colocar anotations de valid e not null futuramente
//o postman mapea de acordo com o nome do dto
public record AvaliacaoDto(Integer nota, String comentario, Long idPonto, Long idRestaurante) {


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
