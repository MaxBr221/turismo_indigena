package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.Guide;
import com.example.projeto_turismo.domains.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GuideDto {
    private Long id;
    private String nome;
    private String telefone;
    private String login;
    private String senha;
    private Role role;


    public GuideDto(Guide guide) {
        BeanUtils.copyProperties(guide, this);
    }

    public GuideDto(Long id, String nome, String telefone, String login, Role role) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.login = login;
        this.role = role;
    }
}
