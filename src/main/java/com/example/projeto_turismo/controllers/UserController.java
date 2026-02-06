package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.dto.RegisterDto;
import com.example.projeto_turismo.dto.UserResponseDto;
import com.example.projeto_turismo.dto.UserUpdateDto;
import com.example.projeto_turismo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<UserResponseDto> create(@RequestBody RegisterDto dto){
        logger.info("Criando usuário");
        User user = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseDto(user));
    }
    @Operation(summary = "Buscando users")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){
        logger.info("Listando todos os usuários");
        List<UserResponseDto> response = userService.findAll()
                .stream()
                .map(UserResponseDto::new)
                .toList();
        return ResponseEntity.ok(response);
    }
    @Operation(summary = "Buscando users")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "200", description = "Busca feita com sucesso")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        logger.info("listando usuário selecionado");
        User user = userService.findById(id);
        return ResponseEntity.ok(new UserResponseDto(user));
    }
    @Operation(summary = "Editando users")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso proibido"),
            @ApiResponse(responseCode = "200", description = "Edição feita com sucesso")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @RequestBody UserUpdateDto userDto){
        logger.info("Atualizando usuário");
        User user = userService.findById(id);
        userService.update(id, userDto);
        return ResponseEntity.ok(new UserResponseDto(user));
    }
    @Operation(summary = "Deletando users")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        logger.info("Deletando usuário");
        userService.delete(id);
    }
}
