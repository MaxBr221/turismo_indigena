package com.example.projeto_turismo.services;


import com.example.projeto_turismo.domains.Avaliacao;
import com.example.projeto_turismo.domains.PontoTuristico;
import com.example.projeto_turismo.domains.Restaurantes;
import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.dto.AvaliacaoDto;
import com.example.projeto_turismo.dto.AvaliacaoResponseDto;
import com.example.projeto_turismo.dto.AvaliacaoUpdateDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {

    private AvaliacaoRepository avaliacaoRepository;
    private PontoTuristicoRepository pontoTuristicoRepository;
    private RestaurantesRepository restaurantesRepository;
    private UserRepository userRepository;
    private UsuarioLogadoProvider usuarioLogadoProvider;
    private RestaurantesService restaurantesService;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, PontoTuristicoRepository pontoTuristicoRepository, RestaurantesRepository restaurantesRepository, UserRepository userRepository, UsuarioLogadoProvider usuarioLogadoProvider) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.pontoTuristicoRepository = pontoTuristicoRepository;
        this.restaurantesRepository = restaurantesRepository;
        this.userRepository = userRepository;
        this.usuarioLogadoProvider = usuarioLogadoProvider;
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

        User user = usuarioLogadoProvider.pegarUsuarioLogado();

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
    public AvaliacaoResponseDto findById(Long id){
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Avaliação desse Ponto Turistico não existente"));


        return new AvaliacaoResponseDto(
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getUser().getId(),
                avaliacao.getPontoTuristico().getId(),
                avaliacao.getRestaurante().getId(),
                avaliacao.getDataAvaliacao());

    }
    public List<AvaliacaoResponseDto> findAll(){
        List<Avaliacao> avaliacaos = avaliacaoRepository.findAll();

        if (avaliacaos.isEmpty()){
            throw new EventFullException("Avaliações inexistente");
        }
        return avaliacaos
                .stream()
                .map(avaliacao -> new AvaliacaoResponseDto(
                        avaliacao.getNota(),
                        avaliacao.getComentario(),
                        avaliacao.getUser().getId(),
                        avaliacao.getPontoTuristico().getId(),
                        avaliacao.getRestaurante().getId(),
                        avaliacao.getDataAvaliacao()))
                .toList();
    }

    public void delete(Long id){
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(()->new EventFullException("Essa avaliação não existe"));
        avaliacaoRepository.delete(avaliacao);
    }

    public void avaliarRestaurante(AvaliacaoDto avaliacaoDto){
        avaliar(avaliacaoDto);
    }

    private void avaliar(AvaliacaoDto avaliacaoDto){
        Avaliacao avaliacao = new Avaliacao();
        User user = usuarioLogadoProvider.pegarUsuarioLogado();

        //finalizar funcionalidade de localização quando estiver fazendo o front
        //conectar uma tela com o back hj

        if(avaliacaoDto.idRestaurante() != null && avaliacaoDto.idPonto() == null){

            Avaliacao avaliacao1 = avaliacaoRepository.findByUserAndRestauranteId(user, avaliacaoDto.idRestaurante());
            if(avaliacao1 != null) {
                throw new EventFullException("Esse usuário já avaliou esse Restaurante");
            }

            Restaurantes restaurantes = restaurantesRepository.findById(avaliacaoDto.idRestaurante())
                    .orElseThrow(()-> new EventFullException("Restaurante não existente"));

            int cont = restaurantes.getAvaliacoes() + 1;
            restaurantes.setAvaliacoes(cont);
            avaliacao.setRestaurante(restaurantes);
            avaliacao.setNota(avaliacaoDto.nota());
            avaliacao.setComentario(avaliacaoDto.comentario());
            avaliacao.setUser(user);
            avaliacaoRepository.save(avaliacao);
            recalcularMediaRestaurante(restaurantes.getId());

        }

        if(avaliacaoDto.idPonto() != null && avaliacaoDto.idRestaurante() == null){

            Avaliacao avaliacao2 = avaliacaoRepository.findByUserAndPontoTuristicoId(user, avaliacaoDto.idPonto());
            if(avaliacao2 != null){
                throw new EventFullException("Esse usuário já avaliou esse Ponto Turistico");
            }

            PontoTuristico pontoTuristico = pontoTuristicoRepository.findById(avaliacaoDto.idPonto())
                    .orElseThrow(()-> new EventFullException("Ponto Turistico não existente"));


            int contAvaliacoes = pontoTuristico.getAvaliacoes() + 1;
            pontoTuristico.setAvaliacoes(contAvaliacoes);
            avaliacao.setPontoTuristico(pontoTuristico);
            avaliacao.setNota(avaliacaoDto.nota());
            avaliacao.setComentario(avaliacaoDto.comentario());
            avaliacao.setUser(user);
            avaliacaoRepository.save(avaliacao);
            recalcularMediaPontoTuristico(pontoTuristico.getId());
        }
        if(avaliacaoDto.idRestaurante() != null && avaliacaoDto.idPonto() != null){
            throw new EventFullException("Não é possivel avaliar os dois ao mesmo tempo");
        }

    }
    public void avaliarPontoTuristico(AvaliacaoDto avaliacaoDto){
        avaliar(avaliacaoDto);
    }

    public void recalcularMediaRestaurante(Long id){
        Restaurantes restaurantes = restaurantesRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Restaurante não existe"));

        Double media = avaliacaoRepository.findByRestauranteId(restaurantes.getId())
                .stream().mapToDouble(avaliacao -> avaliacao.getNota())
                .average()
                .orElse(0.0);

        restaurantes.setMedia(media);
        restaurantesRepository.save(restaurantes);

    }
    public void recalcularMediaPontoTuristico(Long id){
        PontoTuristico pontoTuristico = pontoTuristicoRepository.findById(id)
                .orElseThrow(()-> new EventFullException("ponto turistico não existente"));

        Double media = avaliacaoRepository.findByPontoTuristicoId(pontoTuristico.getId())
                .stream().mapToDouble(avaliacao -> avaliacao.getNota())
                .average()
                .orElse(0.0);

        pontoTuristico.setMedia(media);
        pontoTuristicoRepository.save(pontoTuristico);
    }
}
