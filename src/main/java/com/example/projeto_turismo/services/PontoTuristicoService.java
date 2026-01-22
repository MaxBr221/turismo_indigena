package com.example.projeto_turismo.services;

import com.example.projeto_turismo.domains.PontoTuristico;
import com.example.projeto_turismo.dto.PontoTuristicoDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.PontoTuristicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PontoTuristicoService {
    @Autowired
    private PontoTuristicoRepository repository;

    public PontoTuristicoDto create(PontoTuristicoDto pontoTuristico){

        PontoTuristico turistico = new PontoTuristico();
        turistico.setNome(pontoTuristico.getNome());
        turistico.setLocal(pontoTuristico.getLocal());
        turistico.setTelefone(pontoTuristico.getTelefone());

        PontoTuristico salvo = repository.save(turistico);
        return new PontoTuristicoDto(
                salvo.getId(),
                salvo.getNome(),
                salvo.getLocal(),
                salvo.getTelefone());
    }
    public List<PontoTuristicoDto> findAll(){
        return repository.findAll()
                .stream()
                .map((pontoTuristico1 -> new PontoTuristicoDto(
                        pontoTuristico1.getId(),
                        pontoTuristico1.getNome(),
                        pontoTuristico1.getLocal(),
                        pontoTuristico1.getTelefone())
                ))
                .toList();

    }
    public PontoTuristicoDto findById(Long id){
        PontoTuristico pontoTuristico = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Ponto turistico não encotrado."));
        return new PontoTuristicoDto(
                pontoTuristico.getId(),
                pontoTuristico.getNome(),
                pontoTuristico.getLocal(),
                pontoTuristico.getTelefone());
    }
    public PontoTuristicoDto update(Long id, PontoTuristicoDto dto){
        if(!id.equals(dto.getId())){
            throw new EventFullException("Os dois ids não coincidem");
        }
        PontoTuristico pontoTuristico = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Ponto turistico não encotrado."));

        pontoTuristico.setNome(dto.getNome());
        pontoTuristico.setLocal(dto.getLocal());
        pontoTuristico.setTelefone(dto.getTelefone());

        PontoTuristico pontoTuristico1 = repository.save(pontoTuristico);

        return new PontoTuristicoDto(
                pontoTuristico1.getId(),
                pontoTuristico1.getNome(),
                pontoTuristico1.getLocal(),
                pontoTuristico1.getTelefone());
    }
    public void delete(Long id){
        PontoTuristico pontoTuristico = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Ponto turistico não encotrado."));
        repository.delete(pontoTuristico);

    }
}
