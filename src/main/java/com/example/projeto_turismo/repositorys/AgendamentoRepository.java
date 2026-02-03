package com.example.projeto_turismo.repositorys;

import com.example.projeto_turismo.domains.Agendamento;
import com.example.projeto_turismo.dto.AgendamentoResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    AgendamentoResponseDto findByData(LocalDateTime data);
    Agendamento findByUser(Long id);
}
