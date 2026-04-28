package com.example.projeto_turismo.integration;

import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static com.example.projeto_turismo.common.UserConstants.REGISTER_DTO;
import static com.example.projeto_turismo.common.UserConstants.USER;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("it")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(com.example.projeto_turismo.config.TestSecurityConfig.class)
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


}
