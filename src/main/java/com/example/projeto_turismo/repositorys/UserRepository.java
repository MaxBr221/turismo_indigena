package com.example.projeto_turismo.repositorys;

import com.example.projeto_turismo.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLoginIgnoreCase(String login);
    User findByLogin(String login);

}
