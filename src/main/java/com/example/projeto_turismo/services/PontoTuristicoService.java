package com.example.projeto_turismo.services;

import com.example.projeto_turismo.domains.PontoTuristico;
import com.example.projeto_turismo.dto.PontoTuristicoCreateDto;
import com.example.projeto_turismo.dto.PontoTuristicoResponseDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.PontoTuristicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PontoTuristicoService {
    private PontoTuristicoRepository repository;

    public PontoTuristicoService(PontoTuristicoRepository repository) {
        this.repository = repository;
    }

    public PontoTuristicoResponseDto create(PontoTuristicoCreateDto pontoTuristico){

        PontoTuristico turistico = new PontoTuristico();
        turistico.setNome(pontoTuristico.nome());
        turistico.setLocal(pontoTuristico.local());
        turistico.setInformacoes(pontoTuristico.informacoes());

        PontoTuristico salvo = repository.save(turistico);
        return new PontoTuristicoResponseDto(
                salvo.getId(),
                salvo.getNome(),
                salvo.getLocal(),
                salvo.getInformacoes());
    }
    public List<PontoTuristicoResponseDto> findAll(){
        return repository.findAll()
                .stream()
                .map((pontoTuristico1 -> new PontoTuristicoResponseDto(
                        pontoTuristico1.getId(),
                        pontoTuristico1.getNome(),
                        pontoTuristico1.getLocal(),
                        pontoTuristico1.getInformacoes())
                ))
                .toList();

    }
    public PontoTuristicoResponseDto findById(Long id){
        PontoTuristico pontoTuristico = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Ponto turistico não encotrado."));
        return new PontoTuristicoResponseDto(
                pontoTuristico.getId(),
                pontoTuristico.getNome(),
                pontoTuristico.getLocal(),
                pontoTuristico.getInformacoes());
    }
    public PontoTuristicoResponseDto update(Long id, PontoTuristicoCreateDto dto){
        PontoTuristico pontoTuristico = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Ponto turistico não encotrado."));

        pontoTuristico.setNome(dto.nome());
        pontoTuristico.setLocal(dto.local());
        pontoTuristico.setInformacoes(dto.informacoes());

        PontoTuristico pontoTuristico1 = repository.save(pontoTuristico);

        return new PontoTuristicoResponseDto(
                pontoTuristico1.getId(),
                pontoTuristico1.getNome(),
                pontoTuristico1.getLocal(),
                pontoTuristico1.getInformacoes());
    }
    public void delete(Long id){
        PontoTuristico pontoTuristico = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Ponto turistico não encotrado."));
        repository.delete(pontoTuristico);

    }
}
