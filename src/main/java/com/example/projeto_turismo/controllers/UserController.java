package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.dto.RegisterDto;
import com.example.projeto_turismo.dto.UserResponseDto;
import com.example.projeto_turismo.dto.UserUpdateDto;
import com.example.projeto_turismo.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody RegisterDto dto){
        logger.info("Criando usuário");
        User user = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseDto(user));
    }
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){
        logger.info("Listando todos os usuários");
        List<UserResponseDto> response = userService.findAll()
                .stream()
                .map(UserResponseDto::new)
                .toList();
        return ResponseEntity.ok(response);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        logger.info("listando usuário selecionado");
        User user = userService.findById(id);
        return ResponseEntity.ok(new UserResponseDto(user));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @RequestBody UserUpdateDto userDto){
        logger.info("Atualizando usuário");
        User user = userService.findById(id);
        userService.update(id, userDto);
        return ResponseEntity.ok(new UserResponseDto(user));
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        logger.info("Deletando usuário");
        userService.delete(id);
    }
}
