package com.example.projeto_turismo.domains;

import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.UserRepository;
import com.example.projeto_turismo.repositorys.UsuarioLogadoProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioLogadoProviderImpl implements UsuarioLogadoProvider {
    @Autowired
    private UserRepository repository;

    @Override
    public User pegarUsuarioLogado() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()){
                throw new EventFullException("Usuário não autenticado");
            }
            String login = authentication.getName();
            return repository.findByLogin(login);
        }catch (RuntimeException e){
            throw new RuntimeException("Usuário inválido", e);
        }
    }
}
