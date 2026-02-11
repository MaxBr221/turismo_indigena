package com.example.projeto_turismo.service;

import static com.example.projeto_turismo.common.UserConstants.REGISTER_DTO;
import static com.example.projeto_turismo.common.UserConstants.USER;
import static com.example.projeto_turismo.common.UserConstants.INVALID_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.example.projeto_turismo.domains.Status;
import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.dto.UserDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.UserRepository;
import com.example.projeto_turismo.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Optional;


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

        UserDto user = userService.create(REGISTER_DTO);

        assertThat(user).isEqualTo(USER);
    }
    @Test
    public void createUser_WithInvalidData_ThrowsExeption(){
        when(userRepository.save(any(User.class))).thenThrow(RuntimeException.class);
        assertThatThrownBy(()-> userService.create(INVALID_USER)).isInstanceOf(RuntimeException.class);
    }
    @Test
    public void getByidUser_WithValidData_ReturnUser(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(USER));

        UserDto user = userService.findById(1L);
        assertThat(user.id()).isEqualTo(USER.getId());

    }
    @Test
    public void getUser_ByUnexistingId_ReturnsEmpty(){
        when(userRepository.findById(1L)).thenReturn(Optional.of(USER));

        UserDto user = userService.findById(1L);

        assertThat(user).isNotNull();
        assertThat(user.id()).isEqualTo(1L);
    }
    @Test
    public void getByLoginUser_WithLoginEqualsUser_ReturnsException(){
        when(userRepository.existsByLoginIgnoreCase(USER.getLogin())).thenReturn(true);


        assertThatThrownBy(()-> userService.create(REGISTER_DTO)).isInstanceOf(EventFullException.class);

    }

}
