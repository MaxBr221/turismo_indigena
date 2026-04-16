package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.repositorys.UsuarioLogadoProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meuPerfil")
@Slf4j
public class MeuPerfilControler {

    private UsuarioLogadoProvider repository;

    @GetMapping("/me")
    public ResponseEntity<User> minhasInformacoes(){
        User userSalvo = repository.pegarUsuarioLogado();
        log.info("pegando informaçoes do user");
        return ResponseEntity.ok(userSalvo);

    }
}
