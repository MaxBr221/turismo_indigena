package com.example.projeto_turismo.services;

import com.example.projeto_turismo.domains.*;
import com.example.projeto_turismo.dto.AgendamentoCreateDto;
import com.example.projeto_turismo.dto.AgendamentoResponseDto;
import com.example.projeto_turismo.dto.AgendamentoUpdateDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.AgendamentoRepository;
import com.example.projeto_turismo.repositorys.GuideRepository;
import com.example.projeto_turismo.repositorys.RestaurantesRepository;
import com.example.projeto_turismo.repositorys.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class AgendamentoService {
    private AgendamentoRepository repository;
    private UserRepository userRepository;
    private GuideRepository guideRepository;
    private RestaurantesRepository restaurantesRepository;
    private AuthService authService;

    public AgendamentoService(AgendamentoRepository repository, UserRepository userRepository, GuideRepository guideRepository, RestaurantesRepository restaurantesRepository, AuthService authService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.guideRepository = guideRepository;
        this.restaurantesRepository = restaurantesRepository;
        this.authService = authService;
    }

    public AgendamentoResponseDto create(AgendamentoCreateDto dto) {
        if(repository.findByData(dto.data()) != null){
            throw new EventFullException("Data já escolhida por outro usuário.");
        }
        User user = userRepository.findById(dto.user())
                .orElseThrow(() -> new EventFullException("User não existente"));
        Guide guide = guideRepository.findById(dto.guide())
                .orElseThrow(() -> new EventFullException("Guide não existente"));
        Restaurantes restaurantes = restaurantesRepository.findById(dto.restaurante())
                .orElseThrow(() -> new EventFullException("Restaurante não existente"));

        Agendamento agendamento = new Agendamento();
        agendamento.setData(dto.data());
        agendamento.setQuantPessoas(dto.quantPessoas());
        agendamento.setStatus(Status.AGENDADO);
        agendamento.setDataCriacao(LocalDateTime.now());
        agendamento.setUser(user);
        agendamento.setGuide(guide);
        agendamento.setRestaurante(restaurantes);
        Agendamento ag = repository.save(agendamento);
        return new AgendamentoResponseDto(
                ag.getId(),
                ag.getData(),
                ag.getQuantPessoas(),
                ag.getStatus(),
                ag.getDataCriacao(),
                ag.getUser().getId(),
                ag.getGuide().getId(),
                ag.getRestaurante().getId());
    }
    @Transactional
    public List<AgendamentoResponseDto> findAll(){
        return repository.findAll()
                .stream()
                .map(agendamento -> new AgendamentoResponseDto(
                        agendamento.getId(),
                        agendamento.getData(),
                        agendamento.getQuantPessoas(),
                        agendamento.getStatus(),
                        agendamento.getDataCriacao(),
                        agendamento.getUser().getId(),
                        agendamento.getGuide().getId(),
                        agendamento.getRestaurante().getId()
                ))
                .toList();
    }
    public AgendamentoResponseDto findById(Long id){
        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new EventFullException("Agendamento não encontrado."));
        return new AgendamentoResponseDto(
                agendamento.getId(),
                agendamento.getData(),
                agendamento.getQuantPessoas(),
                agendamento.getStatus(),
                agendamento.getDataCriacao(),
                agendamento.getUser().getId(),
                agendamento.getGuide().getId() ,
                agendamento.getRestaurante().getId());
    }

    public void delete(Long id){
        Agendamento ag = repository.findById(id)
                .orElseThrow(() -> new EventFullException("Agendamento não encontrado."));
        repository.delete(ag);
    }
    public AgendamentoResponseDto update(Long id, AgendamentoUpdateDto ag){
        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new EventFullException("Agendamento não encontrado."));
        agendamento.setData(ag.data());
        agendamento.setStatus(ag.status());
        agendamento.setQuantPessoas(ag.quantPessoas());
        Agendamento salvo = repository.save(agendamento);
        return new AgendamentoResponseDto(
                salvo.getId(),
                salvo.getData(),
                salvo.getQuantPessoas(),
                salvo.getStatus(),
                salvo.getDataCriacao(),
                salvo.getUser().getId(),
                salvo.getGuide().getId(),
                salvo.getRestaurante().getId());
    }
    public List<AgendamentoResponseDto> findByAgendamentoUser(){
        User user = authService.getUserLogado();

        List<Agendamento> ag = repository.findByUser(user);

        if(ag.isEmpty()){
            throw new EventFullException("Não existe agendamento referente a esse usuário");
        }

        return ag.stream()
                .map(agendamento-> new AgendamentoResponseDto(
                        agendamento.getId(),
                        agendamento.getData(),
                        agendamento.getQuantPessoas(),
                        agendamento.getStatus(),
                        agendamento.getDataCriacao(),
                        agendamento.getUser().getId(),
                        agendamento.getGuide().getId(),
                        agendamento.getRestaurante().getId()

                ))
                .toList();
    }
    public List<AgendamentoResponseDto> findByAgendamentoAtivos(){
        List<Agendamento> ag = repository.findByStatus(Status.AGENDADO);
        if(ag.isEmpty()){
            throw new EventFullException("Lista de agendamentos agendados vazia");
        }
        return ag
                .stream()
                .map(agendamento -> new AgendamentoResponseDto(
                agendamento.getId(),
                agendamento.getData(),
                agendamento.getQuantPessoas(),
                agendamento.getStatus(),
                agendamento.getDataCriacao(),
                agendamento.getUser().getId(),
                agendamento.getGuide().getId(),
                agendamento.getRestaurante().getId()
        )).toList();

    }
    public List<AgendamentoResponseDto> findByAgendamentosCancelados(){
        List<Agendamento> ag = repository.findByStatus(Status.CANCELADO);

        if(ag.isEmpty()){
            throw new EventFullException("Não há nenhum agendamento cancelado");
        }
        return ag.stream().map(agendamento -> new AgendamentoResponseDto(
                agendamento.getId(),
                agendamento.getData(),
                agendamento.getQuantPessoas(),
                agendamento.getStatus(),
                agendamento.getDataCriacao(),
                agendamento.getUser().getId(),
                agendamento.getGuide().getId(),
                agendamento.getRestaurante().getId()
        )).toList();
    }
}
