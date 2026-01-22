package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Restaurantes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantesDto {
    private Long id;
    private String nome;
    private String descricao;
    private String tipo;
    private String localizacao;
    private String horarioFuncionamento;
    private String telefone;
    private String redeSociais;

    public RestaurantesDto(String nome, String descricao, String tipo, String localizacao, String horarioFuncionamento, String telefone, String redeSociais) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.localizacao = localizacao;
        this.horarioFuncionamento = horarioFuncionamento;
        this.telefone = telefone;
        this.redeSociais = redeSociais;
    }

    public RestaurantesDto(Restaurantes restaurante){
        BeanUtils.copyProperties(restaurante, this);
    }
}
