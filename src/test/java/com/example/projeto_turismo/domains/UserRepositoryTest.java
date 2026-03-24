package com.example.projeto_turismo.domains;

import com.example.projeto_turismo.dto.UserDto;
import com.example.projeto_turismo.repositorys.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static com.example.projeto_turismo.common.UserConstants.USER;
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void createUser_withValidData_ReturnsUser(){
        User user = new User("Maxsuel", "83991710731", "maxsuel.com.br", Role.USER);
        userRepository.save(user);


    }
}
