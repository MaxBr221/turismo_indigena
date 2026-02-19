package com.example.projeto_turismo.services;

import com.example.projeto_turismo.domains.PageResponse;
import com.example.projeto_turismo.domains.Restaurantes;
import com.example.projeto_turismo.dto.RestaurantesDto;
import com.example.projeto_turismo.dto.RestaurantesResponseDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.RestaurantesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantesService {
    private RestaurantesRepository repository;

    public RestaurantesService(RestaurantesRepository repository) {
        this.repository = repository;
    }

    public RestaurantesResponseDto create(RestaurantesDto restaurantesDto){
        Restaurantes restaurante = new Restaurantes();

        restaurante.setNome(restaurantesDto.nome());
        restaurante.setDescricao(restaurantesDto.descricao());
        restaurante.setLocalizacao(restaurantesDto.localizacao());
        restaurante.setHorarioFuncionamento(restaurantesDto.horarioFuncionamento());
        restaurante.setTelefone(restaurantesDto.telefone());
        restaurante.setRedeSociais(restaurantesDto.redeSociais());
        Restaurantes salvo = repository.save(restaurante);

        return new RestaurantesResponseDto(
                salvo.getId(),
                salvo.getNome(),
                salvo.getDescricao(),
                salvo.getLocalizacao(),
                salvo.getHorarioFuncionamento(),
                salvo.getTelefone(),
                salvo.getRedeSociais());
    }
    public RestaurantesResponseDto findById(Long id){
        Restaurantes restaurante = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Restaurante não encontrado."));

        return new RestaurantesResponseDto(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getDescricao(),
                restaurante.getLocalizacao(),
                restaurante.getHorarioFuncionamento(),
                restaurante.getTelefone(),
                restaurante.getRedeSociais());
    }
    public List<RestaurantesResponseDto> findAll(){
        return repository.findAll()
                .stream()
                .map(restaurantes -> new RestaurantesResponseDto(
                        restaurantes.getId(),
                        restaurantes.getNome(),
                        restaurantes.getDescricao(),
                        restaurantes.getLocalizacao(),
                        restaurantes.getHorarioFuncionamento(),
                        restaurantes.getTelefone(),
                        restaurantes.getRedeSociais()
                ))
                .toList();
    }
    public RestaurantesResponseDto update(Long id, RestaurantesDto restaurantesDto){
        Restaurantes restaurantes = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Restaurante não encontrado"));
        restaurantes.setNome(restaurantesDto.nome());
        restaurantes.setDescricao(restaurantesDto.descricao());
        restaurantes.setLocalizacao(restaurantesDto.localizacao());
        restaurantes.setHorarioFuncionamento(restaurantesDto.horarioFuncionamento());
        restaurantes.setTelefone(restaurantesDto.telefone());
        restaurantes.setRedeSociais(restaurantesDto.redeSociais());
        Restaurantes salvos = repository.save(restaurantes);
        return new RestaurantesResponseDto(
                salvos.getNome(),
                salvos.getDescricao(),
                salvos.getLocalizacao(),
                salvos.getHorarioFuncionamento(),
                salvos.getTelefone(),
                salvos.getRedeSociais());
    }
    public void delete(Long id){
        Restaurantes restaurante = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Restaurante não encontrado."));
        repository.delete(restaurante);
    }
    //debugar esse metodo
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
}
