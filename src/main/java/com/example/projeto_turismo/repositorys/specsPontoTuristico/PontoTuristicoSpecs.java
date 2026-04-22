package com.example.projeto_turismo.repositorys.specsPontoTuristico;

import com.example.projeto_turismo.domains.PontoTuristico;
import com.example.projeto_turismo.domains.Restaurantes;
import org.springframework.data.jpa.domain.Specification;

public class PontoTuristicoSpecs {

    private PontoTuristicoSpecs(){}

    public static Specification<PontoTuristico> nomeLike(String termo){
        return ((root, query, cb) -> cb.like(cb.lower(root.get("nome")), "%" + termo.toLowerCase() + "%"));

    }public static Specification<PontoTuristico> localLike(String termo){
        return ((root, query, cb) -> cb.like(cb.lower(root.get("local")), "%" + termo.toLowerCase() + "%"));

    }
    public static Specification<PontoTuristico> informacoesLike(String termo){
        return ((root, query, cb) -> cb.like(cb.lower(root.get("informacoes")), "%" + termo.toLowerCase() + "%"));

    }
}
