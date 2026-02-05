package com.example.projeto_turismo.service;

import static com.example.projeto_turismo.common.UserConstants.USER;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.services.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserService.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    //operacao_estado_retorno
    @Test
    public void createUser_WithValidData_ReturnUser(){
        User user = userService.create(USER);

        assertThat(user).isEqualTo(USER);


    }
}
