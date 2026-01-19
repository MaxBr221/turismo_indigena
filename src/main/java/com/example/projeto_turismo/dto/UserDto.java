package com.example.projeto_turismo.dto;

import com.example.projeto_turismo.domains.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String nome;
    private String telefone;
    private String login;
    private String senha;

    public UserDto(User user){
        BeanUtils.copyProperties(user, this);

    }

    public UserDto(Long id, String nome, String telefone, String login) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.login = login;
    }

    public UserDto() {
    }
}
