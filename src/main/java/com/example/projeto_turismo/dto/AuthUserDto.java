package com.example.projeto_turismo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Autenticação de Usuário")
public record AuthUserDto(@Schema(description = "Login de Usuário", example = "max.lima@gmail") String login,
                          @Schema(description = "Senha do Usuário", example = "maxbr22") String senha) {
}
