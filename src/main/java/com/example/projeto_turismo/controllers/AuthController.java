package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.dto.AuthDto;
import com.example.projeto_turismo.dto.LoginDto;
import com.example.projeto_turismo.dto.RegisterDto;
import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.infra.security.TokenService;
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
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthDto authDto){
        var userNamePassword = new UsernamePasswordAuthenticationToken(authDto.login(), authDto.senha());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken((User)auth.getPrincipal());
        return ResponseEntity.ok(new LoginDto(token));
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDto registerDto){
        if (repository.findByLogin(registerDto.login())!= null){
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.senha());
        User user = new User(registerDto.nome(), registerDto.telefone(), registerDto.login(), encryptedPassword, registerDto.role());
        repository.save(user);
        return ResponseEntity.ok().build();
    }
}
