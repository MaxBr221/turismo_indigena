package com.example.projeto_turismo.repository;

import com.example.projeto_turismo.domains.Guide;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.repositorys.GuideRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;


//usar essa anotations em todos os repositoryTests
@DataJpaTest(excludeAutoConfiguration = {
        org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration.class
})
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class GuideRepositoryTest {
    @Autowired
    private GuideRepository repository;
    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    void createGuideExistent_WithValidData_ThrowByException(){
        Guide guide = new Guide(1L, "Max", "83991710731", "formado em turismo");
        testEntityManager.persist(guide);
        assertThatThrownBy(()-> repository.save(guide)).isInstanceOf(EventFullException.class);



    }
}
