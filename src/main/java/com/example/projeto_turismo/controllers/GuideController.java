package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.domains.Guide;
import com.example.projeto_turismo.dto.GuideDto;
import com.example.projeto_turismo.dto.GuideResponseDto;
import com.example.projeto_turismo.services.GuideService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guide")
public class GuideController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(GuideController.class.getName());
    @Autowired
    private GuideService service;

    @PostMapping
    public ResponseEntity<GuideResponseDto> create(@RequestBody GuideDto guideDto){
        logger.info("Criando guia");
        GuideResponseDto guide = service.create(guideDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guide);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<GuideResponseDto> findById(@PathVariable Long id){
        logger.info("Buscando guia");
        GuideResponseDto guide = service.findById(id);
        return ResponseEntity.ok().body(guide);
    }
    @GetMapping
    public ResponseEntity<List<GuideResponseDto>> findAll(){
        logger.info("Listando todos os guias");
        List<GuideResponseDto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<GuideResponseDto> update(@PathVariable Long id, @RequestBody GuideDto guide){
        logger.info("Editando guia");
        GuideResponseDto guideResponseDto = service.update(id, guide);
        return ResponseEntity.ok().body(guideResponseDto);
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        logger.info("Apagando guia");
        service.delete(id);
    }

}
