package com.example.projeto_turismo.service;

import static com.example.projeto_turismo.common.UserConstants.REGISTER_DTO;
import static com.example.projeto_turismo.common.UserConstants.USER;
import static com.example.projeto_turismo.common.UserConstants.INVALID_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.repositorys.UserRepository;
import com.example.projeto_turismo.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    //operacao_estado_retorno
    @Test
    public void createUser_WithValidData_ReturnUser(){
        when(userRepository.save(any(User.class))).thenReturn(USER);

        User user = userService.create(REGISTER_DTO);

        assertThat(user).isEqualTo(USER);
    }
    @Test
    public void createUser_WithInvalidData_ThrowsExeption(){
        when(userRepository.save(any(User.class))).thenThrow(RuntimeException.class);
        assertThatThrownBy(()-> userService.create(INVALID_USER)).isInstanceOf(RuntimeException.class);
    }
}
