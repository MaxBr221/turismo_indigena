package com.example.projeto_turismo.repositorys;

import com.example.projeto_turismo.domains.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideRepository extends JpaRepository<Guide, Long> {
}
