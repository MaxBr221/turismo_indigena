package com.example.projeto_turismo.repositorys;

import com.example.projeto_turismo.domains.Agendamento;
import com.example.projeto_turismo.domains.Status;
import com.example.projeto_turismo.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    Agendamento findByData(LocalDateTime data);
    List<Agendamento> findByUser(User user);
    List<Agendamento> findByStatus(Status status);
}
