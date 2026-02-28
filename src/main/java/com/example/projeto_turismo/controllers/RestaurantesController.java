package com.example.projeto_turismo.controllers;

import com.example.projeto_turismo.dto.RestaurantesDto;
import com.example.projeto_turismo.dto.RestaurantesResponseDto;
import com.example.projeto_turismo.services.RestaurantesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.LoggerFactory;
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

@Tag(name = "restaurantes", description = "Endpoits Restaurantes")
@RestController
@RequestMapping("restaurantes")
public class RestaurantesController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(RestaurantesController.class.getName());
    private RestaurantesService service;

    public RestaurantesController(RestaurantesService service) {
        this.service = service;
    }

    @Operation(summary = "Criando Restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criando Restaurante"),
            @ApiResponse(responseCode = "400", description = "Dados incorretos")
    })
    @PostMapping
    public ResponseEntity<RestaurantesResponseDto> create(@RequestBody RestaurantesDto restauranteDto) {
        logger.info("Criando Restaurante");
        RestaurantesResponseDto restaurante = service.create(restauranteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
    }
    @Operation(summary = "Listando Restaurantes")
    @ApiResponse(responseCode = "200", description = "Listando todos os Restaurantes")
    @GetMapping
    public ResponseEntity<List<RestaurantesResponseDto>> findAll(){
        logger.info("Listando todos os restaurantes");
        List<RestaurantesResponseDto> restaurantesResponseDtoList = service.findAll();
        return ResponseEntity.ok().body(restaurantesResponseDtoList);
    }
    @Operation(summary = "Buscando Restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Buscando Restaurante"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<RestaurantesResponseDto> findById(@Parameter(description = "Id do Restaurante que deseja buscar", example = "1")@PathVariable Long id){
        logger.info("Buscando Restaurante");
        RestaurantesResponseDto restaurantesResponseDto = service.findById(id);
        return ResponseEntity.ok().body(restaurantesResponseDto);
    }
    @Operation(summary = "Editando Restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<RestaurantesResponseDto> update(@Parameter(description = "Id do Restaurante que deseja editar", example = "1")@PathVariable Long id, @RequestBody RestaurantesDto restauranteDto){
        logger.info("Atualizando restaurante");
        RestaurantesResponseDto restaurante = service.update(id, restauranteDto);
        return ResponseEntity.ok().body(restaurante);
    }
    @Operation(summary = "Deletando Restaurante")
    @ApiResponse(responseCode = "200", description = "Deletado com sucesso")
    @DeleteMapping(value = "/{id}")
    public void delete(@Parameter(description = "Id do Restaurante que deseja deletar", example = "1")@PathVariable Long id){
        logger.info("Apagando Restaurante");
        service.delete(id);
    }
    @GetMapping("/restaurantePaginacao")
    public ResponseEntity<?> listarPaginas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String direction){

        var response = service.listaPaginada(page, size, sortBy, direction);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/{id}/imagem")
    public ResponseEntity<?> uploadImagem(@PathVariable Long id, @RequestParam("file") MultipartFile file){
        logger.info("Criando imagem de Restaurante");
        service.salvarImagem(id, file);
        return ResponseEntity.ok("Imagem crianda com sucesso");
    }
    @GetMapping("imagem/{nome}")
    public ResponseEntity<Resource> buscarImagem(@RequestParam String nome) throws MalformedURLException {
        logger.info("Buscando imagem de Restaurante");
        Path caminho = Paths.get("uploads/restaurante").resolve(nome);
        Resource resource = new UrlResource(caminho.toUri());
        return ResponseEntity.ok().body(resource);
    }

}
