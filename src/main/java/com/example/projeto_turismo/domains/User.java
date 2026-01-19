package com.example.projeto_turismo.domains;

import com.example.projeto_turismo.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "telefone", nullable = false)
    private String telefone;
    @Column(name = "login", nullable = false, unique = true)
    private String login;
    @Column(name = "senha", nullable = false)
    private String senha;

    public User(UserDto userDto){
        BeanUtils.copyProperties(userDto, this);
    }
}
