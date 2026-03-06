package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.dto.RegisterDto;
import com.example.projeto_turismo.dto.UserDto;
import com.example.projeto_turismo.dto.UserUpdateDto;
import com.example.projeto_turismo.repositorys.UsuarioLogadoProvider;
import com.example.projeto_turismo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "users", description = "Endpoints de Users")
@RestController
@RequestMapping("/users")
public class UserController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class.getName());
    private UserService userService;
    private UsuarioLogadoProvider usuarioLogadoProvider;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Criando users")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criando users"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso proibido")
    })
    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody RegisterDto dto){
        logger.info("Criando usuário");
        UserDto user = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @Operation(summary = "Buscando users")
    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        logger.info("Listando todos os usuários");
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
        logger.info("listando usuário selecionado");
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
    public ResponseEntity<UserDto> update(@RequestBody UserUpdateDto userDto){
        logger.info("Atualizando usuário");
        UserDto user = userService.findById();
        userService.update(userDto);
        return ResponseEntity.ok().body(user);
    }
    @Operation(summary = "Deletando users")
    @DeleteMapping(value = "/me")
    public void delete(){
        logger.info("Deletando usuário");
        userService.delete();
    }
}
