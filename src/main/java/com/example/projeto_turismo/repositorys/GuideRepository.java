package com.example.projeto_turismo.repositorys;

import com.example.projeto_turismo.domains.Guide;
import com.example.projeto_turismo.repositorys.specsGuide.GenerateSpecsGuide;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

import static com.example.projeto_turismo.repositorys.specsGuide.GuideSpecs.descricaoLike;
import static com.example.projeto_turismo.repositorys.specsGuide.GuideSpecs.nomeLike;

public interface GuideRepository extends JpaRepository<Guide, Long>, JpaSpecificationExecutor<Guide> {
    Guide findByTelefone(String telefone);

    default List<Guide> findByNome(String nome){

        Specification<Guide> spec = Specification.where(GenerateSpecsGuide.conjuction());
        if (nome != null){
            spec = spec.and(nomeLike(nome))
                    .or(descricaoLike(nome));
        }
        return findAll(spec);
    }
}
