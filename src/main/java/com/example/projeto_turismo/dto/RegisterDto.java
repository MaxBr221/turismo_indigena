package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Role;

public record RegisterDto(String nome, String telefone, String login, String senha, Role role) {
}
