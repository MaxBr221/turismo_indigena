package com.example.projeto_turismo.repositorys.specsGuide;

import com.example.projeto_turismo.domains.Guide;
import org.springframework.data.jpa.domain.Specification;

public class GuideSpecs {
    private GuideSpecs(){}

    public static Specification<Guide> nomeLike(String termo){
        return ((root, query, cb) -> cb.like(cb.lower(root.get("nome")), "%" + termo.toLowerCase() + "%"));

    }
    public static Specification<Guide> descricaoLike(String termo){
        return ((root, query, cb) -> cb.like(cb.lower(root.get("descricao")), "%" + termo.toLowerCase() + "%"));

    }
}
