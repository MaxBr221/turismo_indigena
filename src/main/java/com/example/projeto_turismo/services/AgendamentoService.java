package com.example.projeto_turismo.services;

import com.example.projeto_turismo.domains.Agendamento;
import com.example.projeto_turismo.dto.AgendamentoDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository repository;

    public AgendamentoDto create(AgendamentoDto dto){
        Agendamento agendamento = new Agendamento();
        agendamento.setData(dto.getData());
        agendamento.setQuantPessoas(dto.getQuantPessoas());
        agendamento.setStatus(dto.getStatus());
        agendamento.setDataCriacao(dto.getDataCriacao());
        Agendamento ag = repository.save(agendamento);
        return new AgendamentoDto(
                ag.getId(),
                ag.getData(),
                ag.getQuantPessoas(),
                ag.getStatus(),
                ag.getDataCriacao());
    }
    public List<AgendamentoDto> findAll(){
        return repository.findAll()
                .stream()
                .map(agendamento -> new AgendamentoDto(
                        agendamento.getId(),
                        agendamento.getData(),
                        agendamento.getQuantPessoas(),
                        agendamento.getStatus(),
                        agendamento.getDataCriacao()
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
                agendamento.getDataCriacao());
    }
    public void delete(Long id){
        Agendamento ag = repository.findById(id)
                .orElseThrow(() -> new EventFullException("Agendamento não encontrado."));
        repository.delete(ag);
    }
    public AgendamentoDto update(AgendamentoDto ag, Long id){
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
                salvo.getDataCriacao());
    }
}
