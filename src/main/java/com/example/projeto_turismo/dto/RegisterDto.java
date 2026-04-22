package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

@Schema(description = "Dto de registro do Usuário")
public record RegisterDto(@Schema(description = "Nome do Usuário", example = "maxsuel") @NotBlank String nome,
                          @Schema(description = "Telefone do Usuário", example = "839911111") @NotBlank String telefone,
                          @Schema(description = "Login do Usuário", example = "maxsuel@gmail.com") @NotBlank String login,
                          @Schema(description = "Senha do Usuário", example = "maxsuelbr222")@NotBlank String senha,
                          @Schema(description = "Tipo do User", example = "USER OR ADMIN") Role role) {
}
