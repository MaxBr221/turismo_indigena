package com.example.projeto_turismo.domains;

import com.example.projeto_turismo.dto.RegisterDto;
import com.example.projeto_turismo.repositorys.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Usuario está no Database")
    void findUserByDocumentSuccess(){
        String login = "maxsue.lima@gmail";
        RegisterDto registerDto = new RegisterDto("Maxsuel Lima", "83991710731", login, "maxbr22", Role.USER);
        this.createUser_withValidData_ReturnsUser(registerDto);
        User userEncotrado = this.userRepository.findByLogin(registerDto.login());

        assertThat(userEncotrado).isEqualTo(login);

    }

    private User createUser_withValidData_ReturnsUser(RegisterDto register){
        User user = new User(register);
        this.testEntityManager.persist(user);
        return user;


    }
}
