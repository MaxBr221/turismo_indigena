package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Local;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de entrada de PontoTuristico")
public record PontoTuristicoCreateDto(@Schema(description = "Nome do PontoTuristico", example = "Prainha")
                                      String nome,
                                      @Schema(description = "Local do PontoTuristico", example = "PRAIA")
                                      Local local,
                                      @Schema(description = "Mais informações do PontoTuristico", example = "Vistas linda dos mar com área ventilada")
                                      String informacoes, String latitude, String longitude) {
}
