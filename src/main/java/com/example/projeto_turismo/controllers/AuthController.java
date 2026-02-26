package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.dto.AuthUserDto;
import com.example.projeto_turismo.dto.LoginDto;
import com.example.projeto_turismo.dto.RegisterDto;
import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.infra.security.TokenService;
import com.example.projeto_turismo.repositorys.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "auth")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository repository;
    private TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, UserRepository repository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthUserDto authUserDto){
        var userNamePassword = new UsernamePasswordAuthenticationToken(authUserDto.login(), authUserDto.senha());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((User)auth.getPrincipal());
        return ResponseEntity.ok(new LoginDto(token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDto registerDto){
        if (repository.findByLogin(registerDto.login())!= null){
            throw new EventFullException("Login de usuário já existente");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.senha());
        User user = new User(registerDto.nome(), registerDto.telefone(), registerDto.login(), encryptedPassword, registerDto.role());
        repository.save(user);
        return ResponseEntity.ok().build();
    }
}
