package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Guide;

public record GuideDto(String nome, String telefone, String descricao) {

    public GuideDto(Guide guide){
        this(guide.getNome(), guide.getTelefone(), guide.getDescricao());
    }
}
