package com.example.projeto_turismo.repositorys;

import com.example.projeto_turismo.domains.Restaurantes;
import com.example.projeto_turismo.repositorys.specsRestaurantes.GenerateSpecsRestaurante;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

import static com.example.projeto_turismo.repositorys.specsRestaurantes.RestauranteSpecs.*;

public interface RestaurantesRepository extends JpaRepository<Restaurantes, Long> , JpaSpecificationExecutor<Restaurantes> {
    Optional<Restaurantes> findFirstByMediaIsNotNullOrderByMediaDesc();

    default List<Restaurantes> findByTermo(String termo){

        Specification<Restaurantes> spec = Specification.where(GenerateSpecsRestaurante.conjuction());
        if (termo != null){
            spec = spec.and(nomeLike(termo))
                    .or(descricaoLike(termo))
                    .or(localizacaoLike(termo));
        }

        return findAll(spec);
    }

}
