package com.example.projeto_turismo.services;

import com.example.projeto_turismo.domains.Restaurantes;
import com.example.projeto_turismo.dto.RestaurantesDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.RestaurantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantesService {

    @Autowired
    private RestaurantesRepository repository;

    public RestaurantesDto create(RestaurantesDto restaurantesDto){
        Restaurantes restaurante = new Restaurantes();

        restaurante.setNome(restaurantesDto.getNome());
        restaurante.setDescricao(restaurantesDto.getDescricao());
        restaurante.setTipo(restaurantesDto.getTipo());
        restaurante.setLocalizacao(restaurantesDto.getLocalizacao());
        restaurante.setHorarioFuncionamento(restaurantesDto.getHorarioFuncionamento());
        restaurante.setTelefone(restaurantesDto.getTelefone());
        restaurante.setRedeSociais(restaurantesDto.getRedeSociais());

        Restaurantes salvo = repository.save(restaurante);

        return new RestaurantesDto(
                salvo.getId(),
                salvo.getNome(),
                salvo.getDescricao(),
                salvo.getTipo(),
                salvo.getLocalizacao(),
                salvo.getHorarioFuncionamento(),
                salvo.getTelefone(),
                salvo.getRedeSociais());
    }
    public RestaurantesDto findById(Long id){
        Restaurantes restaurante = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Restaurante não encontrado."));

        return new RestaurantesDto(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getDescricao(),
                restaurante.getTipo(),
                restaurante.getLocalizacao(),
                restaurante.getHorarioFuncionamento(),
                restaurante.getTelefone(),
                restaurante.getRedeSociais());
    }
    public List<RestaurantesDto> findAll(){
        return repository.findAll()
                .stream()
                .map(restaurantes -> new RestaurantesDto(
                        restaurantes.getId(),
                        restaurantes.getNome(),
                        restaurantes.getDescricao(),
                        restaurantes.getTipo(),
                        restaurantes.getLocalizacao(),
                        restaurantes.getHorarioFuncionamento(),
                        restaurantes.getTelefone(),
                        restaurantes.getRedeSociais()
                ))
                .toList();
    }
    public RestaurantesDto update(Long id, RestaurantesDto restaurantesDto){
        if(!id.equals(restaurantesDto.getId())){
            throw new EventFullException("Os id não coincidem");
        }
        Restaurantes restaurantes = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Restaurante não encontrado"));
        restaurantes.setNome(restaurantesDto.getNome());
        restaurantes.setDescricao(restaurantesDto.getDescricao());
        restaurantes.setTipo(restaurantesDto.getTipo());
        restaurantes.setLocalizacao(restaurantesDto.getLocalizacao());
        restaurantes.setHorarioFuncionamento(restaurantesDto.getHorarioFuncionamento());
        restaurantes.setTelefone(restaurantesDto.getTelefone());
        restaurantes.setRedeSociais(restaurantesDto.getRedeSociais());
        Restaurantes salvos = repository.save(restaurantes);
        return new RestaurantesDto(
                salvos.getNome(),
                salvos.getDescricao(),
                salvos.getTipo(),
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
}
