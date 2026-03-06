package com.example.projeto_turismo.repositorys;

import com.example.projeto_turismo.domains.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByPontoTuristicoId(Long pontoId);
    List<Avaliacao> findByRestauranteId(Long restauranteId);

}
