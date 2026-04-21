package com.example.projeto_turismo.repositorys;

import com.example.projeto_turismo.domains.Restaurantes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantesRepository extends JpaRepository<Restaurantes, Long> {
    Optional<Restaurantes> findFirstByMediaIsNotNullOrderByMediaDesc();

}
