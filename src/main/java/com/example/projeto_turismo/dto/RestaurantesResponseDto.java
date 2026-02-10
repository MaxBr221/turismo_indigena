package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Restaurantes;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Dto de Saida de dados do Restaurante")
public class RestaurantesResponseDto {
    @Schema(description = "Identificador do Restaurante", example = "1")
    private Long id;
    @Schema(description = "Nome do Restaurante", example = "Restaurante do Tingo")
    private String nome;
    @Schema(description = "Descrição do Restaurante", example = "Restaurante com comidas tipicas e etc")
    private String descricao;
    @Schema(description = "Local do Restaurante", example = "Localizado na aldeia Silva")
    private String localizacao;
    @Schema(description = "Horario de funcionamento do Restaurante", example = "08:00 as 15:00")
    private String horarioFuncionamento;
    @Schema(description = "Telefone do Restaurante", example = "830029233")
    private String telefone;
    @Schema(description = "Redes Sociais do Restaurante", example = "Link da Rede Social")
    private String redeSociais;

    public RestaurantesResponseDto(String nome, String descricao, String localizacao, String horarioFuncionamento, String telefone, String redeSociais) {
        this.nome = nome;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.horarioFuncionamento = horarioFuncionamento;
        this.telefone = telefone;
        this.redeSociais = redeSociais;
    }

    public RestaurantesResponseDto(Restaurantes restaurante){
        BeanUtils.copyProperties(restaurante, this);
    }
}
