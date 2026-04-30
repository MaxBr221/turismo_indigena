package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.dto.AuthUserDto;
import com.example.projeto_turismo.dto.LoginDto;
import com.example.projeto_turismo.dto.RegisterDto;
import com.example.projeto_turismo.infra.security.TokenService;
import com.example.projeto_turismo.services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "auth")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService service;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, AuthService service, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.service = service;
        this.tokenService = tokenService;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthUserDto authUserDto){
        log.info("Gerando token através de login e senha");
        var userNamePassword = new UsernamePasswordAuthenticationToken(authUserDto.login(), authUserDto.senha());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((User)auth.getPrincipal());
        return ResponseEntity.ok(new LoginDto(token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDto registerDto){
        log.info("Criando Usuário");
        service.registerUser(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
