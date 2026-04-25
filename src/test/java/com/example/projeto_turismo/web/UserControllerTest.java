package com.example.projeto_turismo.web;

import com.example.projeto_turismo.controllers.UserController;
import com.example.projeto_turismo.domains.Role;
import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.infra.security.SecurityFilter;
import com.example.projeto_turismo.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.projeto_turismo.common.UserConstants.REGISTER_DTO;
import static com.example.projeto_turismo.common.UserConstants.USER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = UserController.class,
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = { SecurityFilter.class, SecurityConfig.class }
        )
)


@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService service;
    private final String urlPost = "/users";
    private final String urlGetId = "/users/me";


    @Test
    public void createUser_WithValidData_RetunrCreated() throws Exception {
        when(service.create(REGISTER_DTO)).thenReturn(USER);
        mockMvc.perform(post(urlPost).content(objectMapper.writeValueAsString(REGISTER_DTO))    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(USER.id()));
    }
    @Test
    public void createUser_WithInvalidData_ReturnsBadRequest()  throws Exception{
        User userInvalid = new User("", "", "", "", Role.USER);

        mockMvc.perform(post(urlPost)
                .content(objectMapper.writeValueAsString(userInvalid))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void createUser_WithExistingName_RetunrnsConflict() throws Exception{
        when(service.create(any())).thenThrow(DataIntegrityViolationException.class);

        mockMvc.perform(post(urlPost)
                .content(objectMapper.writeValueAsString(REGISTER_DTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }
    @Test
    public void getUser_ByExistingId_ReturnsUser() throws Exception{
        when(service.findById()).thenReturn(USER);

        mockMvc.perform(get(urlGetId)
                        .content(objectMapper.writeValueAsString(USER))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(USER.id()));

    }
    @Test
    public void getUser_ByUnexistingId_ReturnsNotFound() throws Exception {
        when(service.findById()).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(get(urlGetId))
                .andExpect(status().isNotFound());

    }
    @Test
    public void getUser_ByExistingName_ReturnsUser() throws Exception{
        when(service.findById()).thenReturn(USER);

        mockMvc.perform(get(urlGetId)
                        .content(objectMapper.writeValueAsString(USER))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(USER.nome()));
    }

    @Test
    public void getUser_ByUnexistingUser_ReturnsNotFound() throws Exception{
        when(service.findById()).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(get(urlGetId))
                .andExpect(status().isNotFound());

    }
}
