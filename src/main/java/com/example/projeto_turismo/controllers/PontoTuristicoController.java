package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.domains.Local;
import com.example.projeto_turismo.domains.PontoTuristico;
import com.example.projeto_turismo.dto.PontoTuristicoCreateDto;
import com.example.projeto_turismo.dto.PontoTuristicoResponseDto;
import com.example.projeto_turismo.services.PontoTuristicoService;
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

@Tag(name = "pontoturistico", description = "Endpoints PontoTuristico")
@RestController
@RequestMapping("/pontoTuristico")
public class PontoTuristicoController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(PontoTuristico.class.getName());
    private PontoTuristicoService service;

    public PontoTuristicoController(PontoTuristicoService service) {
        this.service = service;
    }

    @Operation(summary = "Criando PontoTurisitico")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criando PontoTuristico"),
            @ApiResponse(responseCode = "400", description = "Dados incorretos")
    })
    @PostMapping
    public ResponseEntity<PontoTuristicoResponseDto> create(@RequestBody PontoTuristicoCreateDto pontoTuristicoCreateDto){
        logger.info("Criando ponto turistico");
        PontoTuristicoResponseDto pontoTuristicoResponseDto1 = service.create(pontoTuristicoCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pontoTuristicoResponseDto1);
    }
    @Operation(summary = "Buscando PontoTurisitico")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "dados inválidos"),
            @ApiResponse(responseCode = "200", description = "Busca feita com sucesso")
    })

    @GetMapping(value = "/{id}")
    public ResponseEntity<PontoTuristicoResponseDto> findById(@Parameter(description = "Id do PontoTuristico que deseja buscar", example = "1")@PathVariable Long id){
        logger.info("Buscando ponto turistico");
        PontoTuristicoResponseDto pontoTuristicoResponseDto = service.findById(id);
        return ResponseEntity.ok().body(pontoTuristicoResponseDto);
    }
    @Operation(summary = "Listando todos PontoTurisitico")
    @GetMapping
    public ResponseEntity<List<PontoTuristicoResponseDto>> findAll(){
        logger.info("Listando todos os pontos turisticos");
        List<PontoTuristicoResponseDto> pontoTuristicos = service.findAll();
        return ResponseEntity.ok().body(pontoTuristicos);
    }
    @Operation(summary = "Editando PontoTurisitico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Editando PontoTuristico"),
            @ApiResponse(responseCode = "400", description = "Dados incorretos")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<PontoTuristicoResponseDto> update(@Parameter(description = "Id do PontoTuristico que deseja editar", example = "1")@PathVariable Long id, @RequestBody PontoTuristicoCreateDto pontoTuristicoCreateDto){
        logger.info("Atualizando ponto turistico");
        PontoTuristicoResponseDto pontoTuristicoResponseDto1 = service.update(id, pontoTuristicoCreateDto);
        return ResponseEntity.ok().body(pontoTuristicoResponseDto1);
    }
    @Operation(summary = "Deletando PontoTurisitico")
    @DeleteMapping(value = "/{id}")
    public void delete(@Parameter(description = "Id do PontoTuristico que deseja deletar", example = "1")@PathVariable Long id){
        logger.info("Deletando ponto turistico");
        service.delete(id);
    }
    @Operation(summary = "Buscando PontoTuristico aparti de local escolhido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listando todos os ponto turisticos"),
            @ApiResponse(responseCode = "500", description = "Ponto turistico com local não existente")
    })
    @GetMapping("/locais")
    public ResponseEntity<List<PontoTuristicoResponseDto>> findByLocal(@RequestParam Local local){
        logger.info("Buscando Ponto turistico a parti do local");
        List<PontoTuristicoResponseDto> dto = service.findByLocal(local);
        return ResponseEntity.ok().body(dto);
    }
    @GetMapping("/pontos")
    public ResponseEntity<?> listarPontos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        var response = service.listaPaginado(page, size, sortBy, direction);
        return ResponseEntity.ok(response);
    }

}
