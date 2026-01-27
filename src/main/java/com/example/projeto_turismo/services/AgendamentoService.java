package com.example.projeto_turismo.services;

import com.example.projeto_turismo.domains.Agendamento;
import com.example.projeto_turismo.domains.Guide;
import com.example.projeto_turismo.domains.Restaurantes;
import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.dto.AgendamentoDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.AgendamentoRepository;
import com.example.projeto_turismo.repositorys.GuideRepository;
import com.example.projeto_turismo.repositorys.RestaurantesRepository;
import com.example.projeto_turismo.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

//Adicionar possiveis verificações mais regras de negocios//


@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GuideRepository guideRepository;
    @Autowired
    private RestaurantesRepository restaurantesRepository;

    public AgendamentoDto create(AgendamentoDto dto) {
        User user = userRepository.findById(dto.getUser())
                .orElseThrow(() -> new EventFullException("User não existente"));
        Guide guide = guideRepository.findById(dto.getGuide())
                .orElseThrow(() -> new EventFullException("Guide não existente"));
        Restaurantes restaurantes = restaurantesRepository.findById(dto.getRestaurante())
                .orElseThrow(() -> new EventFullException("Restaurante não existente"));
        Agendamento agendamento = new Agendamento();
        agendamento.setData(dto.getData());
        agendamento.setQuantPessoas(dto.getQuantPessoas());
        agendamento.setStatus(dto.getStatus());
        agendamento.setDataCriacao(LocalDateTime.now());
        agendamento.setUser(user);
        agendamento.setGuide(guide);
        agendamento.setRestaurante(restaurantes);
        Agendamento ag = repository.save(agendamento);
        return new AgendamentoDto(
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
    public List<AgendamentoDto> findAll(){
        return repository.findAll()
                .stream()
                .map(agendamento -> new AgendamentoDto(
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
    public AgendamentoDto findById(Long id){
        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new EventFullException("Agendamento não encontrado."));
        return new AgendamentoDto(
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
    public AgendamentoDto update(Long id, AgendamentoDto ag){
        if (!id.equals(ag.getId())){
            throw new EventFullException("Os Ids não coincidem");
        }
        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new EventFullException("Agendamento não encontrado."));
        agendamento.setData(ag.getData());
        agendamento.setStatus(ag.getStatus());
        agendamento.setQuantPessoas(ag.getQuantPessoas());
        Agendamento salvo = repository.save(agendamento);
        return new AgendamentoDto(
                salvo.getId(),
                salvo.getData(),
                salvo.getQuantPessoas(),
                salvo.getStatus(),
                salvo.getDataCriacao(),
                salvo.getUser().getId(),
                salvo.getGuide().getId(),
                salvo.getRestaurante().getId());
    }
}
