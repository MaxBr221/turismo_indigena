package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.dto.UserDto;
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
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto){
        logger.info("Criando usuário");
        UserDto user = userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        logger.info("Listando todos os usuários");
        List<UserDto> userDto = userService.findAll();
        return ResponseEntity.ok().body(userDto);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){
        logger.info("listando usuário selecionado");
        UserDto userDto = userService.findById(id);
        return ResponseEntity.ok().body(userDto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto){
        logger.info("Atualizando usuário");
        UserDto user = userService.findById(id);
        userService.update(id, userDto);
        return ResponseEntity.ok().body(user);
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        logger.info("Deletando usuário");
        userService.delete(id);
    }
}
