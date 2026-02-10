package com.example.projeto_turismo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de Editar Usuários")
public record UserUpdateDto(@Schema(description = "Nome do Usuário") String nome,
                            @Schema(description = "Telefone do Usuário") String telefone,
                            @Schema(description = "Login do Usuário") String login) {
}
