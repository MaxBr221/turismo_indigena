package com.example.projeto_turismo.services;

import com.example.projeto_turismo.repositorys.AvaliacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {

    private AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }
}
