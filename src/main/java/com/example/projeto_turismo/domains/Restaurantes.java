package com.example.projeto_turismo.domains;

import com.example.projeto_turismo.dto.RestaurantesResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@Entity
@Table(name = "restaurantes")
public class Restaurantes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "localizacao", nullable = false)
    private String localizacao;
    @Column(name = "horariofuncionamento", nullable = false)
    private String horarioFuncionamento;
    @Column(name = "telefone", nullable = false)
    private String telefone;
    @Column(name = "redesociais", nullable = false)
    private String redeSociais;
    @Column(name = "imagem")
    private String imagem;

    public Restaurantes(String nome, String descricao, String localizacao, String horarioFuncionamento, String telefone, String redeSociais) {
        this.nome = nome;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.horarioFuncionamento = horarioFuncionamento;
        this.telefone = telefone;
        this.redeSociais = redeSociais;
    }

    public Restaurantes(RestaurantesResponseDto restauranteDto){
        BeanUtils.copyProperties(restauranteDto, this);
    }

}
