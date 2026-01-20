package com.example.projeto_turismo.repositorys;

import com.example.projeto_turismo.domains.PontoTuristico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PontoTuristicoRepository extends JpaRepository<PontoTuristico, Long> {
}
