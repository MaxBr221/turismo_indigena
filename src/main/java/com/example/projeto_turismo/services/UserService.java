package com.example.projeto_turismo.services;


import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.dto.UserDto;
import com.example.projeto_turismo.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDto create(UserDto userDto){
        if(userRepository.existsByLoginIgnoreCase(userDto.getLogin())){
            throw new EventFullException("Já existe usuário com esse email");
        }
        User user1 = new User();
        user1.setNome(userDto.getNome());
        user1.setTelefone(userDto.getTelefone());
        user1.setLogin(userDto.getLogin());
        user1.setSenha(userDto.getSenha());
        User salvo = userRepository.save(user1);

        return new UserDto(salvo.getId(), salvo.getNome(), salvo.getTelefone(), salvo.getLogin());
    }
    public UserDto findById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Usuário não encontrado."));

        return new UserDto(
                user.getId(),
                user.getNome(),
                user.getTelefone(),
                user.getLogin());
    }
    public List<UserDto> findAll(){
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getNome(),
                        user.getTelefone(),
                        user.getLogin()
                ))
                .toList();
    }
    public void delete(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Usuário não encontrado"));
        userRepository.delete(user);
    }
    public UserDto update(Long id, UserDto userDto){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Usuário não encontrado"));

        user.setNome(userDto.getNome());
        user.setTelefone(userDto.getTelefone());

        if (!user.getLogin().equalsIgnoreCase(userDto.getLogin())) {
            if (userRepository.existsByLoginIgnoreCase(userDto.getLogin())){
                throw new EventFullException("Login já existente");
            }
            user.setLogin(userDto.getLogin());

        }

        User salvo = userRepository.save(user);

        return new UserDto(
                salvo.getId(),
                salvo.getNome(),
                salvo.getTelefone(),
                salvo.getLogin());
    }
}
