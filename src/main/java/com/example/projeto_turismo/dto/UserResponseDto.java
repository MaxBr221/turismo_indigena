package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Role;
import com.example.projeto_turismo.domains.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de saida do Usuário")
public record UserResponseDto(@Schema(description = "Id do Usuário", example = "2")Long id,
                              @Schema(description = "nome do Usuário", example = "Maxsuel") String nome,
                              @Schema(description = "Telefone do Usuário", example = "831222223") String telefone,
                              @Schema(description = "Login do Usuário", example = "maxsuel.limabt") String login,
                              @Schema(description = "Tipo do Usuário", example = "ADMIN") Role role) {
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

