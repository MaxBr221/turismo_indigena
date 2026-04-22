package com.example.projeto_turismo.repositorys.specsPontoTuristico;

import org.springframework.data.jpa.domain.Specification;

public class GenerateSpecsRestaurante {

    private GenerateSpecsRestaurante(){}

    public static<T> Specification<T> conjuction(){
        return((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
    }
}
