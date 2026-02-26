package com.example.projeto_turismo.domains;

import com.example.projeto_turismo.repositorys.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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
        User user = userRepository.save(USER);
        User user1 = testEntityManager.find(User.class, user.getId());
        //arrumar o b.o

        assertThat(user1).isNotNull();
        assertThat(user1.getNome()).isEqualTo(user.getNome());
        assertThat(user1.getLogin()).isEqualTo(user.getLogin());
        assertThat(user1.getTelefone()).isEqualTo(user.getTelefone());
    }
}
