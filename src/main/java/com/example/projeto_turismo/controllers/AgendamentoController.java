package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.dto.AgendamentoDto;
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
    public ResponseEntity<AgendamentoDto> create(@RequestBody AgendamentoDto agendamentoDto){
        logger.info("Criando Agendamento");
        AgendamentoDto ag = service.create(agendamentoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ag);
    }
    @GetMapping
    public ResponseEntity<List<AgendamentoDto>> findAll(){
        logger.info("Listando todos os agendamentos");
        List<AgendamentoDto> ag = service.findAll();
        return ResponseEntity.ok().body(ag);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<AgendamentoDto> findById(@PathVariable Long id){
        logger.info("Buscando Agendamento");
        AgendamentoDto ag = service.findById(id);
        return ResponseEntity.ok().body(ag);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody AgendamentoDto ag){
        logger.info("Atualizando Agendamento");
        AgendamentoDto agendamentoDto = service.update(id, ag);
        return ResponseEntity.ok().body(agendamentoDto);
    }
    @DeleteMapping
    public void delete(@PathVariable Long id){
        logger.info("Apagando Agendamento");
        service.delete(id);
    }
}
