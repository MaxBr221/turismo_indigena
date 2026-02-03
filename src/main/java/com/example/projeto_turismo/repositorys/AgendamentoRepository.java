package com.example.projeto_turismo.repositorys;

import com.example.projeto_turismo.domains.Agendamento;
import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.dto.AgendamentoResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    Optional<Agendamento> findByData(LocalDateTime data);
    List<Agendamento> findByUser(User user);
}
