package com.example.projeto_turismo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dto de Login com token")
public record LoginDto(
       @Schema(description = "Token do Usuário", example = "2219xxmmxw") String token) {
}
