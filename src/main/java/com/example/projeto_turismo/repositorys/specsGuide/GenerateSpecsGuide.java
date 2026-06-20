package com.example.projeto_turismo.repositorys.specsGuide;

import org.springframework.data.jpa.domain.Specification;

public class GenerateSpecsGuide {
    private GenerateSpecsGuide(){}

    public static<T> Specification<T> conjuction(){
        return((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
    }
}
