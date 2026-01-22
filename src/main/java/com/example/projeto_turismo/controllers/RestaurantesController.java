package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.dto.RestaurantesDto;
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
    public ResponseEntity<RestaurantesDto> create(@RequestBody RestaurantesDto restaurantesDto) {
        logger.info("Criando Restaurante");
        RestaurantesDto restaurante = service.create(restaurantesDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
    }
    @GetMapping
    public ResponseEntity<List<RestaurantesDto>> findAll(){
        logger.info("Listando todos os restaurantes");
        List<RestaurantesDto> restaurantesDtoList = service.findAll();
        return ResponseEntity.ok().body(restaurantesDtoList);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<RestaurantesDto> findById(@PathVariable Long id){
        logger.info("Buscando Restaurante");
        RestaurantesDto restaurantesDto = service.findById(id);
        return ResponseEntity.ok().body(restaurantesDto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<RestaurantesDto> update(@PathVariable Long id, @RequestBody RestaurantesDto restaurantesDto){
        logger.info("Atualizando restaurante");
        RestaurantesDto restaurante = service.update(id, restaurantesDto);
        return ResponseEntity.ok().body(restaurante);
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        logger.info("Apagando Restaurante");
        service.delete(id);
    }

}
