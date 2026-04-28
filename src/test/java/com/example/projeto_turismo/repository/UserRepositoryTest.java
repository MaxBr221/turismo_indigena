package com.example.projeto_turismo.repository;

import com.example.projeto_turismo.domains.Role;
import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.dto.RegisterDto;
import com.example.projeto_turismo.repositorys.UserRepository;
import com.example.projeto_turismo.repositorys.UsuarioLogadoProvider;
import com.example.projeto_turismo.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@DataJpaTest(excludeAutoConfiguration = {
        org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration.class
})
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Import(UserService.class)

public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserService service;
    @MockBean
    private UsuarioLogadoProvider usuarioLogadoProvider;


    @Test
    @DisplayName("Usuario está no Database")
    void findUserByDocumentSuccess(){
        String login = "maxsue.lima@gmail";
        RegisterDto registerDto = new RegisterDto("Maxsuel Lima", "83991710731", login, "maxbr22", Role.USER);
        this.createUser_withValidData_ReturnsUser(registerDto);
        User userEncotrado = this.userRepository.findByLogin(login);

        assertThat(userEncotrado.getLogin()).isEqualTo(login);

    }
    private User createUser_withValidData_ReturnsUser(RegisterDto register){
        User user = new User(register);
        this.testEntityManager.persist(user);
        return user;
    }
    @Test
    @DisplayName("Usuario não está no Database")
    void findUserByLoginFailed_WithValidData_ThrowByException(){
        String login = "maxsuel.lima@gmail";
        assertThat(userRepository.findByLogin(login)).isNull();

    }
    @Test
    @DisplayName("User dados invalidos")
    public void createUser_WithInvalidData_ThrowException() {
        User user = new User();
        User userInvalid = new User("", "", "","",  Role.USER);

        assertThatThrownBy(()-> userRepository.save(user));
        assertThatThrownBy(()-> userRepository.save(userInvalid));
    }
    @Test
    public void getUser_ByExistingId_ReturnsUser(){
        User user1 = new User("max", "9929292", "max@", "222",Role.USER);
        User user = testEntityManager.persistFlushFind(user1);
        Optional<User> userOpt = userRepository.findById(user.getId());

        assertThat(userOpt).isNotEmpty();
        assertThat(userOpt.get()).isEqualTo(user);
    }
    @Test
    public void getUser_ByUnexistingId_ReturnsEmpty(){

        Optional<User> userOpt = userRepository.findById(1L);
        assertThat(userOpt).isEmpty();
    }

}
