package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.dto.AgendamentoDto;
import com.example.projeto_turismo.services.AgendamentoService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
