package com.example.projeto_turismo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Autenticação de Usuário")
public record AuthUserDto(
        @NotBlank
        @Schema(description = "Login de Usuário", example = "max.lima@gmail") String login,
                          @NotBlank
                          @Schema(description = "Senha do Usuário", example = "maxbr22") String senha) {
}
