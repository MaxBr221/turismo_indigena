package com.example.projeto_turismo.services;

import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.dto.RegisterDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private UserRepository repository;

    public AuthService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    public User getUserLogado(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            throw new EventFullException("Usuário não autenticado");
        }
        //colocar verificação de senha invalida
        String login = authentication.getName();

        return repository.findByLogin(login);
    }
    public User registerUser(RegisterDto registerDto){
        if (repository.findByLogin(registerDto.login())!= null){
            throw new EventFullException("Login de usuário já existente");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.senha());
        User user = new User(registerDto.nome(), registerDto.telefone(), registerDto.login(), encryptedPassword, registerDto.role());
        repository.save(user);
        return user;
    }
}
