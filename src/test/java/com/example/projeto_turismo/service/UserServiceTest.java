package com.example.projeto_turismo.service;

import static com.example.projeto_turismo.common.UserConstants.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.InstanceOfAssertFactories.LONG;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.example.projeto_turismo.common.UserConstants;
import com.example.projeto_turismo.domains.Status;
import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.dto.UserDto;
import com.example.projeto_turismo.dto.UserUpdateDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.UserRepository;
import com.example.projeto_turismo.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> userService.findById(1L)).isInstanceOf(EventFullException.class);
    }
    @Test
    public void getByLoginUser_WithLoginEqualsUser_ReturnsException(){
        when(userRepository.existsByLoginIgnoreCase(USER.getLogin())).thenReturn(true);

        assertThatThrownBy(()-> userService.create(REGISTER_DTO)).isInstanceOf(EventFullException.class);
    }
    //test de update e delete
    @Test
    public void updateUser_WithValueValid_ReturnUserValid(){
        //chamado no metodo update
        when(userRepository.findById(id)).thenReturn(Optional.of(USER));

        when(userRepository.save(any(User.class))).thenReturn(USER);
        //chamado depois de alterado o user

        UserDto atualizado = userService.update(id, UPDATE_DTO);
        //salvo

        assertThat("novoNome").isEqualTo(atualizado.nome());
        assertThat("novoLogin").isEqualTo(atualizado.login());
        assertThat("92111111").isEqualTo(atualizado.telefone());
    }
    @Test
    public void deleteUser_WithValueValid_ReturnNoContet(){
        when(userRepository.findById(id)).thenReturn(Optional.of(USER));

        doNothing().when(userRepository).delete(USER);

        userService.delete(id);
        verify(userRepository, times(1)).delete(USER);

    }
}
