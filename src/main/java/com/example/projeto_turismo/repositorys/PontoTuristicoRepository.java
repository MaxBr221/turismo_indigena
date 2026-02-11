package com.example.projeto_turismo.repositorys;

import com.example.projeto_turismo.domains.Local;
import com.example.projeto_turismo.domains.PontoTuristico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PontoTuristicoRepository extends JpaRepository<PontoTuristico, Long> {
    List<PontoTuristico> findByLocal(Local local);
}
