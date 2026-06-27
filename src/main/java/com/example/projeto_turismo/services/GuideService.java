package com.example.projeto_turismo.services;

import com.example.projeto_turismo.domains.Guide;
import com.example.projeto_turismo.dto.GuideCreateDto;
import com.example.projeto_turismo.dto.GuideResponseDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.GuideRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuideService {

    private GuideRepository repository;

    public GuideService(GuideRepository repository) {
        this.repository = repository;
    }

    public GuideResponseDto create(GuideCreateDto guideCreateDto){
        if(repository.findByTelefone(guideCreateDto.telefone()) != null){
            throw new EventFullException("Guia já cadastrado");
        }
        Guide guide = new Guide();
        BeanUtils.copyProperties(guideCreateDto, guide);
        Guide salvo = repository.save(guide);

        return new GuideResponseDto(salvo);

    }
    public List<GuideResponseDto> findAll(){
        return repository.findAll()
                .stream()
                .map(guide -> new GuideResponseDto(guide))
                .toList();
    }
    public GuideResponseDto findById(Long id){
        Guide guide = repository.findById(id)
                .orElseThrow(() -> new EventFullException("Id não encontrado."));

        return new GuideResponseDto(guide);
    }
    public GuideResponseDto update(Long id, GuideCreateDto guideCreateDto){
        Guide guide = repository.findById(id)
                .orElseThrow(() -> new EventFullException("Id não encontrado."));

        BeanUtils.copyProperties(guideCreateDto, guide);
        Guide salvo = repository.save(guide);

        return new GuideResponseDto(salvo);
    }
    public void delete(Long id){
        Guide guide = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Guia não encontrado."));
        repository.delete(guide);
    }
    public List<Guide> buscarPorNome(String nome){
        return repository.findByNome(nome);
    }
}
