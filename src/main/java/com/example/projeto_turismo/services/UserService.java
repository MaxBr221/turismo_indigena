package com.example.projeto_turismo.services;

import com.example.projeto_turismo.dto.RegisterDto;
import com.example.projeto_turismo.dto.UserDto;
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

    public UserDto create(RegisterDto dto){
        if(userRepository.existsByLoginIgnoreCase(dto.login())){
            throw new EventFullException("Já existe usuário com esse login");
        }

        User user1 = new User();
        user1.setNome(dto.nome());
        user1.setTelefone(dto.telefone());
        user1.setLogin(dto.login());
        user1.setSenha(dto.senha());
        user1.setRole(dto.role());
        User salvo = userRepository.save(user1);
        return new UserDto(
                salvo.getId(),
                salvo.getNome(),
                salvo.getTelefone(),
                salvo.getLogin(),
                salvo.getRole());

    }
    public UserDto findById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Usuário não encontrado."));
        return new UserDto(
                user.getId(),
                user.getNome(),
                user.getTelefone(),
                user.getLogin(),
                user.getRole()
        );

    }
    public List<UserDto> findAll(){
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(
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
    public UserDto update(Long id, UserUpdateDto dto){
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
        User user1 = userRepository.save(user);
        return new UserDto(
                user1.getId(),
                user1.getNome(),
                user1.getTelefone(),
                user1.getLogin(),
                user1.getRole()
        );

    }
}
