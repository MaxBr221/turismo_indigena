package com.example.projeto_turismo.services;

import com.example.projeto_turismo.domains.Guide;
import com.example.projeto_turismo.dto.GuideDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuideService {
    @Autowired
    private GuideRepository repository;

    public GuideDto create(GuideDto guideDto){
        Guide guide = new Guide();
        guide.setNome(guideDto.getNome());
        guide.setTelefone(guideDto.getTelefone());
        guide.setDescricao(guideDto.getDescricao());
        Guide guide1 = repository.save(guide);

        return new GuideDto(guide1.getId(), guide1.getNome(), guide1.getTelefone(), guide1.getDescricao());

    }
    public List<GuideDto> findAll(){
        return repository.findAll()
                .stream()
                .map(guide -> new GuideDto(
                        guide.getId(),
                        guide.getNome(),
                        guide.getTelefone(),
                        guide.getDescricao()))
                .toList();
    }
    public GuideDto findById(Long id){
        Guide guide = repository.findById(id)
                .orElseThrow(() -> new EventFullException("Id não encontrado."));

        return new GuideDto(
                guide.getId(),
                guide.getNome(),
                guide.getTelefone(),
                guide.getDescricao());
    }
    public GuideDto update(Long id, GuideDto guideDto){
        if(!id.equals(guideDto.getId())){
            throw new EventFullException("Os id não coincidem");
        }
        Guide guide = repository.findById(id)
                .orElseThrow(() -> new EventFullException("Id não encontrado."));

        guide.setNome(guideDto.getNome());
        guide.setTelefone(guideDto.getTelefone());
        guide.setDescricao(guideDto.getDescricao());

        Guide guide1 = repository.save(guide);

        return new GuideDto(
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
