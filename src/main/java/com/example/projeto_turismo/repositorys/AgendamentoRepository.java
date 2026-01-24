package com.example.projeto_turismo.repositorys;

import com.example.projeto_turismo.domains.Agendamento;
import com.example.projeto_turismo.dto.AgendamentoDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    AgendamentoDto findByData(LocalDateTime data);
}
