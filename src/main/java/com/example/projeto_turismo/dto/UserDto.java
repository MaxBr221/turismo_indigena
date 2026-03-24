package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Role;
import com.example.projeto_turismo.domains.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados de saida do Usuário")
public record UserDto(@Schema(description = "Id do Usuário", example = "2") @NotNull Long id,
                      @Schema(description = "nome do Usuário", example = "Maxsuel")@NotBlank String nome,
                      @Schema(description = "Telefone do Usuário", example = "831222223")@NotBlank String telefone,
                      @Schema(description = "Login do Usuário", example = "maxsuel.limabt")@NotBlank String login,
                      @Schema(description = "Tipo do Usuário", example = "ADMIN") Role role) {
    public UserDto(User user) {
        this(
                user.getId(),
                user.getNome(),
                user.getTelefone(),
                user.getLogin(),
                user.getRole()
        );
    }
}

