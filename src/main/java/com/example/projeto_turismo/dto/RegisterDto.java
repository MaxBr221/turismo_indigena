package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Role;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dto de registro do Usuário")
public record RegisterDto(@Schema(description = "Nome do Usuário", example = "maxsuel") String nome,
                          @Schema(description = "Telefone do Usuário", example = "839911111") String telefone,
                          @Schema(description = "Login do Usuário", example = "maxsuel@gmail.com") String login,
                          @Schema(description = "Senha do Usuário", example = "maxsuelbr222") String senha,
                          @Schema(description = "Tipo do User", example = "USER OR ADMIN") Role role) {
}
