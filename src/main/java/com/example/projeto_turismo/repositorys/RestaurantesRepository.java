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

    default List<Restaurantes> findByNome(String nome){

        Specification<Restaurantes> spec = Specification.where(GenerateSpecsRestaurante.conjuction());
        if (nome != null){
            spec = spec.and(nomeLike(nome))
                    .or(descricaoLike(nome))
                    .or(localizacaoLike(nome));
        }

        return findAll(spec);
    }

}
