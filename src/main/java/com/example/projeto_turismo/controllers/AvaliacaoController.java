package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.dto.AvaliacaoDto;
import com.example.projeto_turismo.dto.AvaliacaoResponseDto;
import com.example.projeto_turismo.dto.AvaliacaoUpdateDto;
import com.example.projeto_turismo.services.AvaliacaoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacao")
@Slf4j
public class  AvaliacaoController {

    private AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping
    public ResponseEntity<AvaliacaoResponseDto> create(@RequestBody @Valid AvaliacaoDto avaliacaoDto){
        AvaliacaoResponseDto avaliacao = avaliacaoService.create(avaliacaoDto);
        log.info("Criando Avaliação");
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacao);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDto> findById(@PathVariable Long id){
        AvaliacaoResponseDto avaliacao = avaliacaoService.findById(id);
        log.info("Buscando Avaliação");
        return ResponseEntity.ok().body(avaliacao);
    }
    @GetMapping
    public ResponseEntity<List<AvaliacaoResponseDto>> findAll(){
        List<AvaliacaoResponseDto> avaliacoes = avaliacaoService.findAll();
        log.info("Listando avaliações");
        return ResponseEntity.ok().body(avaliacoes);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDto> update(@PathVariable Long id, @RequestBody @Valid AvaliacaoUpdateDto avaliacaoDto){
        AvaliacaoResponseDto avaliacaoResponseDto = avaliacaoService.update(id, avaliacaoDto);
        log.info("Atualizando avaliação");
        return ResponseEntity.ok().body(avaliacaoResponseDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        avaliacaoService.delete(id);
        log.info("deletando avaliação");
        return ResponseEntity.noContent().build();

    }
    @PostMapping("/avaliarRestaurante")
    public void avaliarRestaurante(@RequestBody AvaliacaoDto avaliacaoDto){
        log.info("Avaliando Restaurante");
        avaliacaoService.avaliarRestaurante(avaliacaoDto);
    }
    @PostMapping("/avaliarPontoTuristico")
    public void avaliarPontoTuristico(@RequestBody @Valid AvaliacaoDto avaliacaoDto){
        log.info("Avaliando Ponto Turistico");
        avaliacaoService.avaliarPontoTuristico(avaliacaoDto);
    }
}
