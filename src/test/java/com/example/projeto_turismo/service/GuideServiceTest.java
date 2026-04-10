package com.example.projeto_turismo.service;

import com.example.projeto_turismo.domains.Guide;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.GuideRepository;
import com.example.projeto_turismo.services.GuideService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GuideServiceTest {

    @Mock
    private GuideRepository repository;

    @Autowired
    @InjectMocks
    private GuideService service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("Buscar por guide com sucesso")
    void findById_WithValidData_RetunsTrue(){
        Long id = 1L;
        Guide guide = new Guide(id, "babau", "22222222", "formado em turismo");
        //quando chamado esse find retorne o objeto guide
        when(this.repository.findById(id)).thenReturn(Optional.of(guide));
        //retorna o objeto guide no userEncontrado
        Optional<Guide> userEncontrado = this.repository.findById(id);

        //verifica se o objeto userEncontrado existe.
        assertThat(userEncontrado).isPresent();
        assertThat("babau").isEqualTo(guide.getNome());

    }

    @Test
    @DisplayName("Busca por guide com fracasso")
    void findById_WithInvalidData_ThrowByException(){
        Long id = 1L;
        when(this.repository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(()-> service.findById(id)).isInstanceOf(EventFullException.class);



    }


}
