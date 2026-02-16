package com.example.projeto_turismo.services;

import com.example.projeto_turismo.domains.Local;
import com.example.projeto_turismo.domains.PageResponse;
import com.example.projeto_turismo.domains.PontoTuristico;
import com.example.projeto_turismo.dto.PontoTuristicoCreateDto;
import com.example.projeto_turismo.dto.PontoTuristicoResponseDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.PontoTuristicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<PontoTuristicoResponseDto> findByLocal(Local local){
        List<PontoTuristico> pontoTuristico = repository.findByLocal(local);

        if (pontoTuristico.isEmpty()){
            throw new EventFullException("Não existe esse ponto turistico");
        }
        return pontoTuristico
                .stream()
                .map(ponto -> new PontoTuristicoResponseDto(
                        ponto.getId(),
                        ponto.getNome(),
                        ponto.getLocal(),
                        ponto.getInformacoes()
                ))
                .toList();
    }
    public PageResponse<PontoTuristicoResponseDto> listaPaginado(int page, int size, String sortBy, String direction){
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<PontoTuristico> pageResult = repository.findAll(pageable);

        List<PontoTuristicoResponseDto> content = pageResult
                .getContent()
                .stream()
                .map(PontoTuristicoResponseDto::new)
                .toList();

        return new PageResponse<>(
                content,
                pageResult.getNumber(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages());

    }
}
