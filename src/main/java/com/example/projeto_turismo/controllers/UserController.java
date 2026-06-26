package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.dto.AvaliacaoResponseDto;
import com.example.projeto_turismo.dto.UserDto;
import com.example.projeto_turismo.dto.UserMeuPerfil;
import com.example.projeto_turismo.dto.UserUpdateDto;
import com.example.projeto_turismo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "users", description = "Endpoints de Users")
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Buscando users")
    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        List<UserDto> listaUsers = userService.findAll();
        log.info("Listando todos os usuários");
        return ResponseEntity.ok().body(listaUsers);
    }
    @Operation(summary = "Buscando users")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "200", description = "Busca feita com sucesso")
    })
    @GetMapping(value = "/me")
    public ResponseEntity<UserDto> findByUser(){
        UserDto user = userService.findByUser();
        log.info("listando usuário selecionado");
        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "Editando users")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso proibido"),
            @ApiResponse(responseCode = "200", description = "Edição feita com sucesso")
    })
    @PutMapping(value = "/me")
    public ResponseEntity<UserDto> update(@RequestBody @Valid UserUpdateDto userDto){
        UserDto user = userService.findByUser();
        userService.update(userDto);
        log.info("Atualizando usuário");
        return ResponseEntity.ok().body(user);
    }
    @Operation(summary = "Deletando users")
    @DeleteMapping(value = "/me")
    public ResponseEntity delete(){
        userService.delete();
        log.info("Deletando usuário");
        return ResponseEntity.noContent().build();

    }
    @GetMapping(value = "/meAvaliacoes")
    public ResponseEntity<List<AvaliacaoResponseDto>> findMyAvaliacao(){
        List<AvaliacaoResponseDto> avaliacoes = userService.findMyAvaliacao();
        log.info("Buscando minhas avaliações");
        return ResponseEntity.ok().body(avaliacoes);
    }
    @GetMapping(value = "/mePerfil")
    public ResponseEntity<UserMeuPerfil> meuPerfil(){
        UserMeuPerfil user = userService.meuPerfil();
        log.info("Pegando informações do meu perfil");
        return ResponseEntity.ok(user);
    }
}
