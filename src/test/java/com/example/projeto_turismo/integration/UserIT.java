package com.example.projeto_turismo.integration;

import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.example.projeto_turismo.common.UserConstants.REGISTER_DTO;
import static com.example.projeto_turismo.common.UserConstants.USER;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("it")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(com.example.projeto_turismo.config.TestSecurityConfig.class)
@Sql(scripts = {"/import_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/remover_users.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createUser_ReturnsCreated(){
        ResponseEntity<UserDto> sut = restTemplate.postForEntity("/users", REGISTER_DTO, UserDto.class);
        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(sut.getBody().id()).isNotNull();
        assertThat(sut.getBody().nome()).isEqualTo(USER.nome());
        assertThat(sut.getBody().login()).isEqualTo(USER.login());
    }
    @Test
    @WithMockUser(username = "max@gmail", roles = {"USER"})
    public void getUser_ReturnUser(){
        ResponseEntity<List<UserDto>> sut = restTemplate.exchange("/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDto>>() {}
        );
        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sut.getBody()).isNotEmpty();

    }


}
