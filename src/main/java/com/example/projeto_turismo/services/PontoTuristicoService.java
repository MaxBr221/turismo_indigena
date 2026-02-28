package com.example.projeto_turismo.services;

import com.example.projeto_turismo.domains.Local;
import com.example.projeto_turismo.domains.PageResponse;
import com.example.projeto_turismo.domains.PontoTuristico;
import com.example.projeto_turismo.dto.PontoTuristicoCreateDto;
import com.example.projeto_turismo.dto.PontoTuristicoDtoLocalizacao;
import com.example.projeto_turismo.dto.PontoTuristicoResponseDto;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.PontoTuristicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

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
        turistico.setLatitude(pontoTuristico.latitude());
        turistico.setLongitude(pontoTuristico.longitude());

        PontoTuristico salvo = repository.save(turistico);
        return new PontoTuristicoResponseDto(
                salvo.getId(),
                salvo.getNome(),
                salvo.getLocal(),
                salvo.getInformacoes(),
                salvo.getLatitude(),
                salvo.getLongitude());
    }
    public List<PontoTuristicoResponseDto> findAll(){
        return repository.findAll()
                .stream()
                .map((pontoTuristico1 -> new PontoTuristicoResponseDto(
                        pontoTuristico1.getId(),
                        pontoTuristico1.getNome(),
                        pontoTuristico1.getLocal(),
                        pontoTuristico1.getInformacoes(),
                        pontoTuristico1.getLatitude(),
                        pontoTuristico1.getLongitude())

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
                pontoTuristico.getInformacoes(),
                pontoTuristico.getLatitude(),
                pontoTuristico.getLongitude());
    }
    public PontoTuristicoResponseDto update(Long id, PontoTuristicoCreateDto dto){
        PontoTuristico pontoTuristico = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Ponto turistico não encotrado."));

        //colocar opção de editar latitude e longitude
        pontoTuristico.setNome(dto.nome());
        pontoTuristico.setLocal(dto.local());
        pontoTuristico.setInformacoes(dto.informacoes());

        PontoTuristico pontoTuristico1 = repository.save(pontoTuristico);

        return new PontoTuristicoResponseDto(
                pontoTuristico1.getId(),
                pontoTuristico1.getNome(),
                pontoTuristico1.getLocal(),
                pontoTuristico1.getInformacoes(),
                pontoTuristico1.getLatitude(),
                pontoTuristico1.getLongitude());
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
                        ponto.getInformacoes(),
                        ponto.getLatitude(),
                        ponto.getLongitude()
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
    public void salvarImagem(Long id, MultipartFile file){
        PontoTuristico ponto = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Ponto Turistico não existente"));

        if(!file.getContentType().startsWith("image/")){
            throw new EventFullException("Arquivo inválido");
        }
        try{
            Path pasta = Paths.get("uploads");
            if(!Files.exists(pasta)){
                Files.createDirectories(pasta);
            }
            String nomeArquivo = UUID.randomUUID() + ".jpg";
            Path caminho = pasta.resolve(nomeArquivo);

            Files.copy(file.getInputStream(), caminho, StandardCopyOption.REPLACE_EXISTING);
            ponto.setImagem(nomeArquivo);
            repository.save(ponto);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar imagem");
        }
    }
    public PontoTuristicoResponseDto adicionarLocalizacao(Long id, PontoTuristicoDtoLocalizacao dtoLocalizacao){
        PontoTuristico pontoTuristico = repository.findById(id)
                .orElseThrow(()-> new EventFullException("Ponto Turistico não existente"));

        if(pontoTuristico.getLatitude() != null || pontoTuristico.getLongitude() != null) {
            throw new EventFullException("Localização já existente");
        }
        pontoTuristico.setLatitude(dtoLocalizacao.latitude());
        pontoTuristico.setLongitude(dtoLocalizacao.longitude());

        PontoTuristico ponto = repository.save(pontoTuristico);

        return new PontoTuristicoResponseDto(
                ponto.getId(),
                ponto.getNome(),
                ponto.getLocal(),
                ponto.getInformacoes(),
                ponto.getLatitude(),
                ponto.getLongitude());
    }

}
