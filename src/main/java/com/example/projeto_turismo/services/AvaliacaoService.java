package com.example.projeto_turismo.services;


import com.example.projeto_turismo.domains.Avaliacao;
import com.example.projeto_turismo.domains.PontoTuristico;
import com.example.projeto_turismo.domains.Restaurantes;
import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.dto.AvaliacaoDto;
import com.example.projeto_turismo.dto.AvaliacaoResponseDto;
import com.example.projeto_turismo.dto.AvaliacaoUpdateDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.AvaliacaoRepository;
import com.example.projeto_turismo.repositorys.PontoTuristicoRepository;
import com.example.projeto_turismo.repositorys.RestaurantesRepository;
import com.example.projeto_turismo.repositorys.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {

    private AvaliacaoRepository avaliacaoRepository;
    private PontoTuristicoRepository pontoTuristicoRepository;
    private RestaurantesRepository restaurantesRepository;
    private UserRepository userRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, PontoTuristicoRepository pontoTuristicoRepository, RestaurantesRepository restaurantesRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.pontoTuristicoRepository = pontoTuristicoRepository;
        this.restaurantesRepository = restaurantesRepository;
    }

    public AvaliacaoResponseDto create(AvaliacaoDto avaliacaoDto){
        if(avaliacaoDto.idPonto() == null && avaliacaoDto.idRestaurante() == null){
            throw new EventFullException("É necessário que você avalie pelo menos um");
        }
        if(avaliacaoDto.idPonto() != null && avaliacaoDto.idRestaurante() != null){
            throw new EventFullException("Não é permitido ter duas avaliações com o mesmo destino");
        }
        PontoTuristico ponto = null;
        if (avaliacaoDto.idPonto() != null){
            ponto = pontoTuristicoRepository.findById(avaliacaoDto.idPonto())
                    .orElseThrow(()-> new EventFullException("Ponto turistico não existente para avaliação"));
        }
        Restaurantes restaurante = null;
        if (avaliacaoDto.idRestaurante() != null){
            restaurante = restaurantesRepository.findById(avaliacaoDto.idRestaurante())
                    .orElseThrow(()-> new EventFullException("Restaurante não existente para avaliação"));
        }
        User user = userRepository.findById(avaliacaoDto.idUser())
                .orElseThrow(()-> new EventFullException("Usuário não existente, não é possivel fazer a avaliação"));

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(avaliacaoDto.nota());
        avaliacao.setComentario(avaliacaoDto.comentario());
        avaliacao.setUser(user);
        avaliacao.setPontoTuristico(ponto);
        avaliacao.setRestaurante(restaurante);

        return new AvaliacaoResponseDto(
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getUser().getId(),
                avaliacao.getPontoTuristico().getId(),
                avaliacao.getRestaurante().getId(),
                avaliacao.getDataAvaliacao());
    }
    public AvaliacaoResponseDto update(Long id, AvaliacaoUpdateDto avaliacaoUpdateDto){
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Essa avaliação não existe"));

        if(avaliacaoUpdateDto.pontoId() != null && avaliacaoUpdateDto.restauranteId() != null){
            throw new EventFullException("Não é permitido alterar ponto turistico e restaurante junto");
        }

        PontoTuristico ponto = null;
        if(avaliacaoUpdateDto.pontoId() != null) {
            ponto = pontoTuristicoRepository.findById(avaliacaoUpdateDto.pontoId())
                    .orElseThrow(() -> new EventFullException("Esse ponto turistico não existe"));
        }
        Restaurantes restaurantes = null;
        if(avaliacaoUpdateDto.pontoId() != null) {
            restaurantes = restaurantesRepository.findById(avaliacaoUpdateDto.restauranteId())
                    .orElseThrow(() -> new EventFullException("Esse restaurante não existe"));
        }
        avaliacao.setNota(avaliacaoUpdateDto.nota());
        avaliacao.setComentario(avaliacaoUpdateDto.comentario());
        avaliacao.setPontoTuristico(ponto);
        avaliacao.setRestaurante(restaurantes);

        Avaliacao salvo = avaliacaoRepository.save(avaliacao);

        return new AvaliacaoResponseDto(
                salvo.getNota(),
                salvo.getComentario(),
                salvo.getUser().getId(),
                salvo.getPontoTuristico().getId(),
                salvo.getRestaurante().getId(),
                salvo.getDataAvaliacao());
    }
}
