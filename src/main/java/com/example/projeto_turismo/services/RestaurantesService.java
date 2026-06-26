package com.example.projeto_turismo.services;

import com.example.projeto_turismo.domains.PageResponse;
import com.example.projeto_turismo.domains.Restaurantes;
import com.example.projeto_turismo.dto.RestauranteMediaDto;
import com.example.projeto_turismo.dto.RestaurantesDto;
import com.example.projeto_turismo.dto.RestaurantesResponseDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.RestaurantesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class RestaurantesService {
    private RestaurantesRepository repository;

    public RestaurantesService(RestaurantesRepository repository) {
        this.repository = repository;
    }

    public RestaurantesResponseDto create(RestaurantesDto restaurantesDto){
        Restaurantes restaurante = new Restaurantes();
        BeanUtils.copyProperties(restaurantesDto, restaurante);
        Restaurantes salvo = repository.save(restaurante);
        return new RestaurantesResponseDto(salvo);
    }
    public RestaurantesResponseDto findById(Long id){
        Restaurantes restaurante = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Restaurante não encontrado."));

        return new RestaurantesResponseDto(restaurante);

    }
    public List<RestaurantesResponseDto> findAll(){
        return repository.findAll()
                .stream()
                .map(restaurantes -> new RestaurantesResponseDto(restaurantes))
                .toList();
    }
    public RestaurantesResponseDto update(Long id, RestaurantesDto restaurantesDto){
        Restaurantes restaurantes = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Restaurante não encontrado"));

        BeanUtils.copyProperties(restaurantesDto, restaurantes);
        Restaurantes salvos = repository.save(restaurantes);
        return new RestaurantesResponseDto(salvos);
    }
    public void delete(Long id){
        Restaurantes restaurante = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Restaurante não encontrado."));
        repository.delete(restaurante);
    }
    public PageResponse<RestaurantesResponseDto> listaPaginada(int page, int size, String sortBy, String direction){
        Sort sort = direction.equalsIgnoreCase("desc") ?
                    Sort.by(sortBy).descending() :
                    Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Restaurantes> pageResult = repository.findAll(pageable);
        List<RestaurantesResponseDto> content = pageResult
                .getContent()
                .stream()
                .map(RestaurantesResponseDto::new)
                .toList();
        return new PageResponse<>(
                content,
                pageResult.getNumber(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages());
    }
    public void salvarImagem(Long id, MultipartFile file){
        Restaurantes restaurante = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Restaurante não existente"));
        try{
            String nomeArquivo = UUID.randomUUID() + ".jpg";
            Path caminho = Paths.get("uploads/restaurante/" + nomeArquivo);

            Files.copy(file.getInputStream(), caminho, StandardCopyOption.REPLACE_EXISTING);
            restaurante.setImagem(nomeArquivo);
            repository.save(restaurante);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public RestauranteMediaDto restauranteMaiorMedia(){
        return repository.findFirstByMediaIsNotNullOrderByMediaDesc()
                .map(RestauranteMediaDto::new)
                .orElseThrow(()-> new EventFullException("Nenhum restaurante foi avaliado até o momento"));
    }

    public RestaurantesResponseDto buscarLocalizacaoRestaurante(Long id) {
        Restaurantes restaurante = repository.findById(id)
                .orElseThrow(() -> new EventFullException("Restaurante não existente"));

        return new RestaurantesResponseDto(restaurante);
    }
    public List<Restaurantes> searchRestaurante(String nome){
        return repository.findByNome(nome);


    }

}
