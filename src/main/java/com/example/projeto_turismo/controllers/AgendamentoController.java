package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.dto.AgendamentoCreateDto;
import com.example.projeto_turismo.dto.AgendamentoResponseDto;
import com.example.projeto_turismo.dto.AgendamentoUpdateDto;
import com.example.projeto_turismo.services.AgendamentoService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(AgendamentoController.class.getName());

    @Autowired
    private AgendamentoService service;

    @PostMapping
    public ResponseEntity<AgendamentoResponseDto> create(@RequestBody AgendamentoCreateDto agendamentoCreateDto){
        logger.info("Criando Agendamento");
        AgendamentoResponseDto ag = service.create(agendamentoCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ag);
    }
    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDto>> findAll(){
        logger.info("Listando todos os agendamentos");
        List<AgendamentoResponseDto> ag = service.findAll();
        return ResponseEntity.ok().body(ag);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<AgendamentoResponseDto> findById(@PathVariable Long id){
        logger.info("Buscando Agendamento");
        AgendamentoResponseDto ag = service.findById(id);
        return ResponseEntity.ok().body(ag);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody AgendamentoUpdateDto ag){
        logger.info("Atualizando Agendamento");
        AgendamentoResponseDto agendamentoResponseDto = service.update(id, ag);
        return ResponseEntity.ok().body(agendamentoResponseDto);
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        logger.info("Apagando Agendamento");
        service.delete(id);
    }
    @GetMapping("/meus")
    public ResponseEntity<List<AgendamentoResponseDto>> findByAgendamentUser(){
        logger.info("Buscando Agendamentos do usuário");
        return ResponseEntity.ok(service.findByUserLogado());
    }
}
