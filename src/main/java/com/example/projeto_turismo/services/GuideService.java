package com.example.projeto_turismo.services;

import com.example.projeto_turismo.domains.Guide;
import com.example.projeto_turismo.dto.GuideDto;
import com.example.projeto_turismo.dto.GuideResponseDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuideService {
    @Autowired
    private GuideRepository repository;

    public GuideResponseDto create(GuideDto guideDto){
        Guide guide = new Guide();
        guide.setNome(guideDto.nome());
        guide.setTelefone(guideDto.telefone());
        guide.setDescricao(guideDto.descricao());
        Guide guide1 = repository.save(guide);

        return new GuideResponseDto(guide1.getId(), guide1.getNome(), guide1.getTelefone(), guide1.getDescricao());

    }
    public List<GuideResponseDto> findAll(){
        return repository.findAll()
                .stream()
                .map(guide -> new GuideResponseDto(
                        guide.getId(),
                        guide.getNome(),
                        guide.getTelefone(),
                        guide.getDescricao()))
                .toList();
    }
    public GuideResponseDto findById(Long id){
        Guide guide = repository.findById(id)
                .orElseThrow(() -> new EventFullException("Id não encontrado."));

        return new GuideResponseDto(
                guide.getId(),
                guide.getNome(),
                guide.getTelefone(),
                guide.getDescricao());
    }
    public GuideResponseDto update(Long id, GuideDto guideDto){
        Guide guide = repository.findById(id)
                .orElseThrow(() -> new EventFullException("Id não encontrado."));

        guide.setNome(guideDto.nome());
        guide.setTelefone(guideDto.telefone());
        guide.setDescricao(guideDto.descricao());

        Guide guide1 = repository.save(guide);

        return new GuideResponseDto(
                guide1.getId(),
                guide1.getNome(),
                guide1.getTelefone(),
                guide1.getDescricao());
    }
    public void delete(Long id){
        Guide guide = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Guia não encontrado."));
        repository.delete(guide);
    }
}
