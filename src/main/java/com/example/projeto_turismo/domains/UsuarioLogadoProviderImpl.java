package com.example.projeto_turismo.domains;

import com.example.projeto_turismo.repositorys.UsuarioLogadoProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioLogadoProviderImpl implements UsuarioLogadoProvider {

    @Override
    public User pegarUsuarioLogado() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            return user;
        }catch (RuntimeException e){
            throw new RuntimeException("Usuário inválido", e);
        }
    }
}
