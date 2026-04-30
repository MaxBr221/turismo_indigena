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
        log.info("Listando todos os usuários");
        List<UserDto> listaUsers = userService.findAll();
        return ResponseEntity.ok().body(listaUsers);
    }
    @Operation(summary = "Buscando users")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "200", description = "Busca feita com sucesso")
    })
    @GetMapping(value = "/me")
    public ResponseEntity<UserDto> findById(){
        log.info("listando usuário selecionado");
        UserDto user = userService.findById();
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
        log.info("Atualizando usuário");
        UserDto user = userService.findById();
        userService.update(userDto);
        return ResponseEntity.ok().body(user);
    }
    @Operation(summary = "Deletando users")
    @DeleteMapping(value = "/me")
    public ResponseEntity delete(){
        log.info("Deletando usuário");
        userService.delete();
        return ResponseEntity.noContent().build();

    }
    @GetMapping(value = "/meAvaliacoes")
    public ResponseEntity<List<AvaliacaoResponseDto>> findMyAvaliacao(){
        log.info("Buscando minhas avaliações");
        List<AvaliacaoResponseDto> avaliacoes = userService.findMyAvaliacao();
        return ResponseEntity.ok().body(avaliacoes);
    }
    @GetMapping(value = "/mePerfil")
    public ResponseEntity<UserMeuPerfil> meuPerfil(){
        log.info("Pegando informações do meu perfil");
        UserMeuPerfil user = userService.meuPerfil();
        return ResponseEntity.ok(user);
    }
}
