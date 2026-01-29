package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.domains.PontoTuristico;
import com.example.projeto_turismo.dto.PontoTuristicoDto;
import com.example.projeto_turismo.dto.PontoTuristicoResponseDto;
import com.example.projeto_turismo.services.PontoTuristicoService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pontoTuristico")
public class PontoTuristicoController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(PontoTuristico.class.getName());

    @Autowired
    private PontoTuristicoService service;

    @PostMapping
    public ResponseEntity<PontoTuristicoResponseDto> create(@RequestBody PontoTuristicoDto pontoTuristicoDto){
        logger.info("Criando ponto turistico");
        PontoTuristicoResponseDto pontoTuristicoResponseDto1 = service.create(pontoTuristicoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pontoTuristicoResponseDto1);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<PontoTuristicoResponseDto> findById(@PathVariable Long id){
        logger.info("Buscando ponto turistico");
        PontoTuristicoResponseDto pontoTuristicoResponseDto = service.findById(id);
        return ResponseEntity.ok().body(pontoTuristicoResponseDto);
    }
    @GetMapping
    public ResponseEntity<List<PontoTuristicoResponseDto>> findAll(){
        logger.info("Listando todos os pontos turisticos");
        List<PontoTuristicoResponseDto> pontoTuristicos = service.findAll();
        return ResponseEntity.ok().body(pontoTuristicos);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<PontoTuristicoResponseDto> update(@PathVariable Long id, @RequestBody PontoTuristicoDto pontoTuristicoDto){
        logger.info("Atualizando ponto turistico");
        PontoTuristicoResponseDto pontoTuristicoResponseDto1 = service.update(id, pontoTuristicoDto);
        return ResponseEntity.ok().body(pontoTuristicoResponseDto1);
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        logger.info("Deletando ponto turistico");
        service.delete(id);
    }

}
