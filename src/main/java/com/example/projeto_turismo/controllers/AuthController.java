package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.domains.Role;
import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.dto.AuthUserDto;
import com.example.projeto_turismo.dto.LoginDto;
import com.example.projeto_turismo.dto.RegisterDto;
import com.example.projeto_turismo.infra.security.TokenService;
import com.example.projeto_turismo.repositorys.UserRepository;
import com.example.projeto_turismo.services.AuthService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Tag(name = "auth")
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService service;
    private final TokenService tokenService;
    private final AuthService authService;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, AuthService service, TokenService tokenService, AuthService authService, UserRepository repository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.service = service;
        this.tokenService = tokenService;
        this.authService = authService;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthUserDto authUserDto){
        authService.autenticar(authUserDto.login(),authUserDto.senha());
        var userNamePassword = new UsernamePasswordAuthenticationToken(authUserDto.login(), authUserDto.senha());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((User)auth.getPrincipal());
        log.info("usuário logando..");
        return ResponseEntity.ok(new LoginDto(token));


    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDto registerDto){
        service.registerUser(registerDto);
        log.info("Usuário criado com sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> request) {
        String googleToken = request.get("token");
        String telefoneVindoDoFront = request.get("telefone");

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList("299016155532-2ni3ks08l2lrraugdn1delclh1cru4bc.apps.googleusercontent.com"))
                .build();

        try {
            GoogleIdToken idToken = verifier.verify(googleToken);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();

                String login = payload.getEmail();
                String nome = (String) payload.get("name");

                // 1. Busca se o usuário já existe
                var user = repository.findByLogin(login);
                User usuarioLogado; // Variable de escopo global do método para armazenar quem vai logar

                if (user == null) {
                    // 2. Se não existir, cria o usuário novo
                    User userNovo = new User();
                    userNovo.setLogin(login);
                    userNovo.setNome(nome);
                    userNovo.setRole(Role.USER);

                    if (telefoneVindoDoFront != null && !telefoneVindoDoFront.isEmpty()) {
                        userNovo.setTelefone(telefoneVindoDoFront);
                    } else {
                        userNovo.setTelefone("Não informado");
                    }

                    userNovo.setSenha(passwordEncoder.encode(UUID.randomUUID().toString()));

                    // Salva no banco e joga o resultado na nossa variável de controle
                    usuarioLogado = repository.save(userNovo);
                    log.info("Novo usuário cadastrado via Google: " + login);
                } else {
                    // 3. Se já existir, a variável de controle recebe o usuário encontrado
                    usuarioLogado = user;
                    log.info("Usuário existente logando via Google: " + login);
                }
                String token = tokenService.generateToken(usuarioLogado);
                return ResponseEntity.ok(new LoginDto(token));

            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token Google Inválido");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro na validação: " + e.getMessage());
        }
    }
}
