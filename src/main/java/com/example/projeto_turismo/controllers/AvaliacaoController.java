package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.dto.AvaliacaoDto;
import com.example.projeto_turismo.dto.AvaliacaoResponseDto;
import com.example.projeto_turismo.dto.AvaliacaoUpdateDto;
import com.example.projeto_turismo.services.AvaliacaoService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(GuideController.class.getName());
    private AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping
    public ResponseEntity<AvaliacaoResponseDto> create(@RequestBody @Valid AvaliacaoDto avaliacaoDto){
        logger.info("Criando Avaliação");
        AvaliacaoResponseDto avaliacao = avaliacaoService.create(avaliacaoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacao);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDto> findById(@PathVariable Long id){
        logger.info("Buscando Avaliação");
        AvaliacaoResponseDto avaliacao = avaliacaoService.findById(id);
        return ResponseEntity.ok().body(avaliacao);
    }
    @GetMapping()
    public ResponseEntity<List<AvaliacaoResponseDto>> findAll(){
        logger.info("Listando avaliações");
        List<AvaliacaoResponseDto> avaliacoes = avaliacaoService.findAll();
        return ResponseEntity.ok().body(avaliacoes);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDto> update(@PathVariable Long id, @RequestBody @Valid AvaliacaoUpdateDto avaliacaoDto){
        logger.info("Atualizando avaliação");
        AvaliacaoResponseDto avaliacaoResponseDto = avaliacaoService.update(id, avaliacaoDto);
        return ResponseEntity.ok().body(avaliacaoResponseDto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        logger.info("deletando avaliação");
        avaliacaoService.delete(id);
    }
    @PostMapping("/avaliarRestaurante")
    public void avaliarRestaurante(@RequestBody @Valid AvaliacaoDto avaliacaoDto){
        logger.info("Avaliando Restaurante");
        avaliacaoService.avaliarRestaurante(avaliacaoDto);
    }
    @PostMapping("/avaliarPontoTuristico")
    public void avaliarPontoTuristico(@RequestBody @Valid AvaliacaoDto avaliacaoDto){
        logger.info("Avaliando Ponto Turistico");
        avaliacaoService.avaliarPontoTuristico(avaliacaoDto);
    }
}
