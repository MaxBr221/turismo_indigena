package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.dto.AgendamentoCreateDto;
import com.example.projeto_turismo.dto.AgendamentoResponseDto;
import com.example.projeto_turismo.dto.AgendamentoUpdateDto;
import com.example.projeto_turismo.services.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Agendamentos", description = "Endpoints de agendamento")
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(AgendamentoController.class.getName());
    private AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @Operation(summary = "Criar Agendamento")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "agendamento criado"),
            @ApiResponse(responseCode = "400", description = "dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso proibido")})
    @PostMapping
    public ResponseEntity<AgendamentoResponseDto> create(@RequestBody AgendamentoCreateDto agendamentoCreateDto){
        logger.info("Criando Agendamento");
        AgendamentoResponseDto ag = service.create(agendamentoCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ag);
    }
    @Operation(summary = "Buscando Agendamento")
    @ApiResponse(responseCode = "200", description = "Listagem feita com sucesso")
    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDto>> findAll(){
        logger.info("Listando todos os agendamentos");
        List<AgendamentoResponseDto> ag = service.findAll();
        return ResponseEntity.ok().body(ag);
    }
    @Operation(summary = "Listando Agendamentos")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "dados inválidos"),
            @ApiResponse(responseCode = "200", description = "Busca feita com sucesso")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<AgendamentoResponseDto> findById(@Parameter(description = "Id do Agendamento", example = "1") @PathVariable Long id){
        logger.info("Buscando Agendamento");
        AgendamentoResponseDto ag = service.findById(id);
        return ResponseEntity.ok().body(ag);
    }

    @Operation(summary = "Editando Agendamento")
    @ApiResponses({
        @ApiResponse(responseCode = "dados inválidos"),
        @ApiResponse(responseCode = "403", description = "Acesso proibido")})
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@Parameter(description = "Id do agendamento que deseja editar", example = "1")@PathVariable Long id, @RequestBody AgendamentoUpdateDto ag){
        logger.info("Atualizando Agendamento");
        AgendamentoResponseDto agendamentoResponseDto = service.update(id, ag);
        return ResponseEntity.ok().body(agendamentoResponseDto);
    }
    @Operation(summary = "Deletando Agendamento")
    @DeleteMapping(value = "/{id}")
    public void delete(@Parameter(description = "Id do agendamento que deseja deletar", example = "1")@PathVariable Long id){
        logger.info("Apagando Agendamento");
        service.delete(id);
    }
    @Operation(summary = "Buscando Agendamentos do usuário")
    @ApiResponse(responseCode = "200", description = "Busca feita com sucesso")
    @GetMapping("/meus")
    public ResponseEntity<List<AgendamentoResponseDto>> findByAgendamentUser(){
        logger.info("Buscando Agendamentos do usuário");
        return ResponseEntity.ok(service.findByAgendamentoUser());
    }
    @Operation(summary = "Buscando Agendamentos ativos do usuário")
    @ApiResponse(responseCode = "200", description = "Busca feita com sucesso")
    @GetMapping("/agendados")
    public ResponseEntity<List<AgendamentoResponseDto>> findByAgendamentosAtivo(){
        logger.info("Buscando agendamentos ativos");
        return ResponseEntity.ok().body(service.findByAgendamentoAtivos());
    }
    @Operation(summary = "Buscando Agendamentos cancelados do usuário")
    @ApiResponse(responseCode = "200", description = "Busca feita com sucesso")
    @GetMapping("/cancelados")
    public ResponseEntity<List<AgendamentoResponseDto>> findByAgendamentosCancelados(){
        logger.info("Buscando agendamentos cancelados");
        return ResponseEntity.ok().body(service.findByAgendamentosCancelados());
    }
}
