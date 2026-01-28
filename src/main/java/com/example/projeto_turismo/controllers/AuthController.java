package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.domains.AuthDto;
import com.example.projeto_turismo.domains.RegisterDto;
import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthDto authDto){
        var userNamePassword = new UsernamePasswordAuthenticationToken(authDto.login(), authDto.senha());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDto registerDto){
        if (repository.findByLogin(registerDto.login())!= null){
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.senha());
        User user = new User(registerDto.login(), registerDto.senha(), registerDto.role());
        repository.save(user);
        return ResponseEntity.ok().build();
    }

    //resolver bug do nome null do user//
}
