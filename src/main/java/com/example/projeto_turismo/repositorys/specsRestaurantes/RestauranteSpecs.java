package com.example.projeto_turismo.repositorys.specsRestaurantes;

import com.example.projeto_turismo.domains.Restaurantes;
import org.springframework.data.jpa.domain.Specification;

public class RestauranteSpecs {

    private RestauranteSpecs(){}

    public static Specification<Restaurantes> nomeLike(String termo){
        return ((root, query, cb) -> cb.like(cb.lower(root.get("nome")), "%" + termo.toLowerCase() + "%"));

    }
    public static Specification<Restaurantes> descricaoLike(String termo){
        return ((root, query, cb) -> cb.like(cb.lower(root.get("descricao")), "%" + termo.toLowerCase() + "%"));

    }
    public static Specification<Restaurantes> localizacaoLike(String termo){
        return ((root, query, cb) -> cb.like(cb.lower(root.get("localizacao")), "%" + termo.toLowerCase() + "%"));

    }
}
