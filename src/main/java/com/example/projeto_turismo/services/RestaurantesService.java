package com.example.projeto_turismo.services;

import com.example.projeto_turismo.domains.PageResponse;
import com.example.projeto_turismo.domains.Restaurantes;
import com.example.projeto_turismo.dto.RestauranteMediaDto;
import com.example.projeto_turismo.dto.RestaurantesDto;
import com.example.projeto_turismo.dto.RestaurantesResponseDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.RestaurantesRepository;
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
import java.util.Comparator;
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
        //adicionar o atributo de imagem (em um dto também talvez)
        restaurante.setNome(restaurantesDto.nome());
        restaurante.setDescricao(restaurantesDto.descricao());
        restaurante.setLocalizacao(restaurantesDto.localizacao());
        restaurante.setHorarioFuncionamento(restaurantesDto.horarioFuncionamento());
        restaurante.setTelefone(restaurantesDto.telefone());
        restaurante.setRedeSociais(restaurantesDto.redeSociais());
        Restaurantes salvo = repository.save(restaurante);

        return new RestaurantesResponseDto(
                salvo.getNome(),
                salvo.getDescricao(),
                salvo.getLocalizacao(),
                salvo.getHorarioFuncionamento(),
                salvo.getTelefone(),
                salvo.getRedeSociais(),
                salvo.getLatitude(),
                salvo.getLongitude());
    }
    public RestaurantesResponseDto findById(Long id){
        Restaurantes restaurante = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Restaurante não encontrado."));

        return new RestaurantesResponseDto(
                restaurante.getNome(),
                restaurante.getDescricao(),
                restaurante.getLocalizacao(),
                restaurante.getHorarioFuncionamento(),
                restaurante.getTelefone(),
                restaurante.getRedeSociais(),
                restaurante.getLatitude(),
                restaurante.getLongitude());
    }
    public List<RestaurantesResponseDto> findAll(){
        return repository.findAll()
                .stream()
                .map(restaurantes -> new RestaurantesResponseDto(
                        restaurantes.getNome(),
                        restaurantes.getDescricao(),
                        restaurantes.getLocalizacao(),
                        restaurantes.getHorarioFuncionamento(),
                        restaurantes.getTelefone(),
                        restaurantes.getRedeSociais(),
                        restaurantes.getLatitude(),
                        restaurantes.getLongitude()
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
//        restaurantes.setLatitude(restaurantesDto.latitude());
//        restaurantes.setLongitude(restaurantesDto.longitude());
        Restaurantes salvos = repository.save(restaurantes);
        return new RestaurantesResponseDto(
                salvos.getNome(),
                salvos.getDescricao(),
                salvos.getLocalizacao(),
                salvos.getHorarioFuncionamento(),
                salvos.getTelefone(),
                salvos.getRedeSociais(),
                salvos.getLatitude(),
                salvos.getLongitude());
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
        List<Restaurantes> restaurantes = repository.findAll();

        return restaurantes.stream().filter(restaurantes1 -> restaurantes1.getMedia() != null)
                .max(Comparator.comparing(Restaurantes:: getMedia))
                .map(restaurantes1 -> new RestauranteMediaDto(
                        restaurantes1.getNome(),
                        restaurantes1.getDescricao(),
                        restaurantes1.getLocalizacao(),
                        restaurantes1.getHorarioFuncionamento(),
                        restaurantes1.getTelefone(),
                        restaurantes1.getRedeSociais(),
                        restaurantes1.getMedia(),
                        restaurantes1.getComentario(),
                        restaurantes1.getAvaliacoes()))
                .orElse(null);
    }
    public RestaurantesResponseDto buscarLocalizacaoRestaurante(Long id) {
        Restaurantes restaurante = repository.findById(id)
                .orElseThrow(() -> new EventFullException("Restaurante não existente"));

        return new RestaurantesResponseDto(
                restaurante.getNome(),
                restaurante.getDescricao(),
                restaurante.getLocalizacao(),
                restaurante.getHorarioFuncionamento(),
                restaurante.getTelefone(),
                restaurante.getRedeSociais(),
                restaurante.getLatitude(),
                restaurante.getLongitude());
    }

}
