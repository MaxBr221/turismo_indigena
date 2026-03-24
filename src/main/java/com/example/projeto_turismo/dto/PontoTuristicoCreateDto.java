package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Local;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados de entrada de PontoTuristico")
public record PontoTuristicoCreateDto(@Schema(description = "Nome do PontoTuristico", example = "Prainha")
                                      @NotBlank
                                      String nome,
                                      @Schema(description = "Local do PontoTuristico", example = "PRAIA")
                                      Local local,
                                      @Schema(description = "Mais informações do PontoTuristico", example = "Vistas linda dos mar com área ventilada")
                                      @NotBlank
                                      String informacoes,
                                      @NotNull
                                      Double latitude,
                                      @NotNull
                                      Double longitude) {
}
