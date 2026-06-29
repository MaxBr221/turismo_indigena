package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Restaurantes;

public record RestauranteMediaDto(Long id, String nome, String descricao, String localizacao, String horarioFuncionamento, String telefone, String redeSociais, Double media, String comentario, int avaliacoes) {

    public RestauranteMediaDto (Restaurantes restaurante){
        this(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getDescricao(),
                restaurante.getLocalizacao(),
                restaurante.getHorarioFuncionamento(),
                restaurante.getTelefone(),
                restaurante.getRedeSociais(),
                restaurante.getMedia(),
                restaurante.getComentario(),
                restaurante.getAvaliacoes());
    }
}
