package com.example.projeto_turismo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dto de criação de Restaurante")
public record RestaurantesDto(@Schema(description = "Nome do Restaurante", example = "Restaurante do Tingo") String nome,
                              @Schema(description = "Descrição do Restaurante", example = "Restaurante com comidas tipicas e etc") String descricao,
                              @Schema(description = "Local do Restaurante", example = "Localizado na aldeia Silva") String localizacao,
                              @Schema(description = "Horario de funcionamento do Restaurante", example = "08:00 as 15:00") String horarioFuncionamento,
                              @Schema(description = "Telefone do Restaurante", example = "830029233") String telefone,
                              @Schema(description = "Redes Sociais do Restaurante", example = "Link da Rede Social") String redeSociais) {
}
