package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.dto.RestaurantesDto;
import com.example.projeto_turismo.dto.RestaurantesResponseDto;
import com.example.projeto_turismo.services.RestaurantesService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurantes")
public class RestaurantesController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(RestaurantesController.class.getName());

    @Autowired
    RestaurantesService service;

    @PostMapping
    public ResponseEntity<RestaurantesResponseDto> create(@RequestBody RestaurantesDto restauranteDto) {
        logger.info("Criando Restaurante");
        RestaurantesResponseDto restaurante = service.create(restauranteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
    }
    @GetMapping
    public ResponseEntity<List<RestaurantesResponseDto>> findAll(){
        logger.info("Listando todos os restaurantes");
        List<RestaurantesResponseDto> restaurantesResponseDtoList = service.findAll();
        return ResponseEntity.ok().body(restaurantesResponseDtoList);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<RestaurantesResponseDto> findById(@PathVariable Long id){
        logger.info("Buscando Restaurante");
        RestaurantesResponseDto restaurantesResponseDto = service.findById(id);
        return ResponseEntity.ok().body(restaurantesResponseDto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<RestaurantesResponseDto> update(@PathVariable Long id, @RequestBody RestaurantesDto restauranteDto){
        logger.info("Atualizando restaurante");
        RestaurantesResponseDto restaurante = service.update(id, restauranteDto);
        return ResponseEntity.ok().body(restaurante);
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        logger.info("Apagando Restaurante");
        service.delete(id);
    }

}
