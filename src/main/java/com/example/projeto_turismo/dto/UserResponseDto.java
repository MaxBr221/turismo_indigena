package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Role;
import com.example.projeto_turismo.domains.User;

public record UserResponseDto(Long id, String nome, String telefone, String login, Role role) {
    public UserResponseDto(User user) {
        this(
                user.getId(),
                user.getNome(),
                user.getTelefone(),
                user.getLogin(),
                user.getRole()
        );
    }
}

