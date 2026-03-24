package com.example.projeto_turismo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dto de criação de Restaurante")
public record RestaurantesDto(@Schema(description = "Nome do Restaurante", example = "Restaurante do Tingo") @NotBlank String nome,
                              @Schema(description = "Descrição do Restaurante", example = "Restaurante com comidas tipicas e etc")@NotBlank String descricao,
                              @Schema(description = "Local do Restaurante", example = "Localizado na aldeia Silva")@NotBlank String localizacao,
                              @Schema(description = "Horario de funcionamento do Restaurante", example = "08:00 as 15:00")@NotBlank String horarioFuncionamento,
                              @Schema(description = "Telefone do Restaurante", example = "830029233")@NotBlank String telefone,
                              @Schema(description = "Redes Sociais do Restaurante", example = "Link da Rede Social")@NotBlank String redeSociais,
                              String latitude,
                              String longitude) {
}
