package com.example.projeto_turismo.repositorys;

import com.example.projeto_turismo.domains.Local;
import com.example.projeto_turismo.domains.PontoTuristico;
import com.example.projeto_turismo.repositorys.specsPontoTuristico.GenerateSpecsRestaurante;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

import static com.example.projeto_turismo.repositorys.specsPontoTuristico.PontoTuristicoSpecs.*;

public interface PontoTuristicoRepository extends JpaRepository<PontoTuristico, Long>, JpaSpecificationExecutor<PontoTuristico> {
    List<PontoTuristico> findByLocal(Local local);
    Optional<PontoTuristico> findFirstByMediaIsNotNullOrderByMediaDesc();

    default List<PontoTuristico> findByTermo(String termo){
        Specification<PontoTuristico> spec = Specification.where(GenerateSpecsRestaurante.conjuction());

        if(termo != null){
            spec = spec.and(nomeLike(termo))
                    .or(localLike(termo))
                    .or(informacoesLike(termo));
        }
        return findAll(spec);
    }
}
