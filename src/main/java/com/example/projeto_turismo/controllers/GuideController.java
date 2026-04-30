package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.dto.GuideCreateDto;
import com.example.projeto_turismo.dto.GuideResponseDto;
import com.example.projeto_turismo.services.GuideService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "guide", description = "Endpoints guide")
@RestController
@RequestMapping("/guide")
@Slf4j
public class GuideController {
    private GuideService service;

    public GuideController(GuideService service) {
        this.service = service;
    }

    @Operation(summary = "Criando guide")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criando Guide"),
            @ApiResponse(responseCode = "400", description = "Dados Inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso não permitido")
    })
    @PostMapping
    public ResponseEntity<GuideResponseDto> create(@RequestBody @Valid GuideCreateDto guideCreateDto){
        log.info("Criando guia");
        GuideResponseDto guide = service.create(guideCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guide);
    }
    @Operation(summary = "Buscando guide")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Buscando Guide"),
            @ApiResponse(responseCode = "400", description = "Dados Inválidos")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<GuideResponseDto> findById(@Parameter(description = "Id do Guide que deseja Buscar", example = "1")@PathVariable Long id){
        log.info("Buscando guia");
        GuideResponseDto guide = service.findById(id);
        return ResponseEntity.ok().body(guide);
    }
    @Operation(summary = "listando todos os guides")
    @ApiResponse(responseCode = "200", description = "Listando guides")
    @GetMapping
    public ResponseEntity<List<GuideResponseDto>> findAll(){
        log.info("Listando todos os guias");
        List<GuideResponseDto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @Operation(summary = "Editando Guide")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Editando Guide"),
            @ApiResponse(responseCode = "400", description = "Dados Inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso não permitido")

    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<GuideResponseDto> update(@Parameter(description = "Id do Guide que deseja editar", example = "1")@PathVariable Long id, @RequestBody @Valid GuideCreateDto guide){
        log.info("Editando guia");
        GuideResponseDto guideResponseDto = service.update(id, guide);
        return ResponseEntity.ok().body(guideResponseDto);
    }
    @Operation(summary = "Deletando Guide")
    @ApiResponse(responseCode = "200", description = "Deletando Guide")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@Parameter(description = "Id do Guide que deseja deletar", example = "1")@PathVariable Long id){
        log.info("Apagando guia");
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

}
