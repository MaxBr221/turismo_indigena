package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.domains.Local;
import com.example.projeto_turismo.domains.PontoTuristico;
import com.example.projeto_turismo.dto.PontoTuristicoCreateDto;
import com.example.projeto_turismo.dto.PontoTuristicoMediaDto;
import com.example.projeto_turismo.dto.PontoTuristicoResponseDto;
import com.example.projeto_turismo.services.PontoTuristicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Tag(name = "pontoturistico", description = "Endpoints PontoTuristico")
@RestController
@RequestMapping("/pontoTuristico")
@Slf4j
public class PontoTuristicoController {
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
    public ResponseEntity<PontoTuristicoResponseDto> create(@RequestBody @Valid PontoTuristicoCreateDto pontoTuristicoCreateDto){
        PontoTuristicoResponseDto pontoTuristicoResponseDto1 = service.create(pontoTuristicoCreateDto);
        log.info("Criando ponto turistico");
        return ResponseEntity.status(HttpStatus.CREATED).body(pontoTuristicoResponseDto1);
    }
    @Operation(summary = "Buscando PontoTurisitico")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "dados inválidos"),
            @ApiResponse(responseCode = "200", description = "Busca feita com sucesso")
    })

    @GetMapping(value = "/{id}")
    public ResponseEntity<PontoTuristicoResponseDto> findById(@Parameter(description = "Id do PontoTuristico que deseja buscar", example = "1")@PathVariable Long id){
        PontoTuristicoResponseDto pontoTuristicoResponseDto = service.findById(id);
        log.info("Buscando ponto turistico");
        return ResponseEntity.ok().body(pontoTuristicoResponseDto);
    }
    @Operation(summary = "Listando todos PontoTurisitico")
    @GetMapping
    public ResponseEntity<List<PontoTuristicoResponseDto>> findAll(){
        List<PontoTuristicoResponseDto> pontoTuristicos = service.findAll();
        log.info("Listando todos os pontos turisticos");
        return ResponseEntity.ok().body(pontoTuristicos);
    }
    @Operation(summary = "Editando PontoTurisitico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Editando PontoTuristico"),
            @ApiResponse(responseCode = "400", description = "Dados incorretos")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<PontoTuristicoResponseDto> update(@Parameter(description = "Id do PontoTuristico que deseja editar", example = "1")@PathVariable Long id, @RequestBody @Valid PontoTuristicoCreateDto pontoTuristicoCreateDto){
        PontoTuristicoResponseDto pontoTuristicoResponseDto1 = service.update(id, pontoTuristicoCreateDto);
        log.info("Atualizando ponto turistico");
        return ResponseEntity.ok().body(pontoTuristicoResponseDto1);
    }
    @Operation(summary = "Deletando PontoTurisitico")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@Parameter(description = "Id do PontoTuristico que deseja deletar", example = "1")@PathVariable Long id){
        service.delete(id);
        log.info("Deletando ponto turistico");
        return ResponseEntity.noContent().build();

    }
    @Operation(summary = "Buscando PontoTuristico aparti de local escolhido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listando todos os ponto turisticos"),
            @ApiResponse(responseCode = "500", description = "Ponto turistico com local não existente")
    })
    @GetMapping("/locais")
    public ResponseEntity<List<PontoTuristicoResponseDto>> findByLocal(@RequestParam Local local){
        List<PontoTuristicoResponseDto> dto = service.findByLocal(local);
        log.info("Buscando Ponto turistico a parti do local");
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
        log.info("Listando os ponto turisticos paginado");
        return ResponseEntity.ok(response);
    }
    @PostMapping("/{id}/imagem")
    public ResponseEntity<?> uploadImagem(@PathVariable Long id, @RequestParam("file")MultipartFile file){
        service.salvarImagem(id, file);
        log.info("Criando Imagem de Ponto Turistico");
        return ResponseEntity.ok("Imagem criada com sucesso!");
    }
    @GetMapping("/imagem/{nome}")
    public ResponseEntity<Resource> carregarImagem(@PathVariable String nome) throws MalformedURLException {
        Path caminho = Paths.get("uploads/pontoTuristico/").resolve(nome);
        Resource resource = new UrlResource(caminho.toUri());
        log.info("Buscando Imagem de Ponto Turistico");
        return ResponseEntity.ok().body(resource);
    }
    @PostMapping("/{id}/localizacao")
    public ResponseEntity<PontoTuristicoResponseDto> buscarLocalizacao(@PathVariable Long id){
        PontoTuristicoResponseDto dto = service.buscarLocalizacaoPonto(id);
        log.info("Buscando Imagem de Ponto Turistico");
        return ResponseEntity.ok().body(dto);
    }
    @GetMapping("/melhorAvaliados")
    public ResponseEntity<PontoTuristicoMediaDto> maiorMedia(){
        PontoTuristicoMediaDto pontoTuristicoMediaDto = service.pontoTuristicoMaiorMedia();
        log.info("Buscando o melhor avaliado");
        return ResponseEntity.ok().body(pontoTuristicoMediaDto);
    }
    @GetMapping("/busca")
    public ResponseEntity<List<PontoTuristico>> search(@RequestParam String nome){
        List<PontoTuristico> pontoTuristicos = service.searchPontoTuristico(nome);
        log.info("Buscando de forma dinamica Ponto Turistico");
        return ResponseEntity.ok(pontoTuristicos);
    }
}
