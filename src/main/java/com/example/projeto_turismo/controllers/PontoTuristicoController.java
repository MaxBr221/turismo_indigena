package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.services.PontoTuristicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pontoTuristico")
public class PontoTuristicoController {
    @Autowired
    private PontoTuristicoService service;
}
