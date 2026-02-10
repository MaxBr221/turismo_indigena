package com.example.projeto_turismo.services;

import com.example.projeto_turismo.dto.RegisterDto;
import com.example.projeto_turismo.dto.UserResponseDto;
import com.example.projeto_turismo.dto.UserUpdateDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.repositorys.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //colocar um verificação se o Role == USER
    public UserResponseDto create(RegisterDto dto){
        if(userRepository.existsByLoginIgnoreCase(dto.login())){
            throw new EventFullException("Já existe usuário com esse email");
        }

        User user1 = new User();
        user1.setNome(dto.nome());
        user1.setTelefone(dto.telefone());
        user1.setLogin(dto.login());
        user1.setSenha(dto.senha());
        user1.setRole(dto.role());
        User salvo = userRepository.save(user1);
        return new UserResponseDto(
                salvo.getId(),
                salvo.getNome(),
                salvo.getTelefone(),
                salvo.getLogin(),
                salvo.getRole());

    }
    //retornar dto aqui
    public UserResponseDto findById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Usuário não encontrado."));
        return new UserResponseDto(
                user.getId(),
                user.getNome(),
                user.getTelefone(),
                user.getLogin(),
                user.getRole()
        );

    }
    public List<UserResponseDto> findAll(){
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getNome(),
                        user.getTelefone(),
                        user.getLogin(),
                        user.getRole()
                ))
                .toList();
    }
    public void delete(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Usuário não encontrado"));
        userRepository.delete(user);
    }
    public UserResponseDto update(Long id, UserUpdateDto dto){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Usuário não encontrado"));

        user.setNome(dto.nome());
        user.setTelefone(dto.telefone());

        if (!user.getLogin().equalsIgnoreCase(dto.login())) {
            if (userRepository.existsByLoginIgnoreCase(dto.login())){
                throw new EventFullException("Login já existente");
            }
            user.setLogin(dto.login());

        }
        return new UserResponseDto(userRepository.save(user));

    }
}
