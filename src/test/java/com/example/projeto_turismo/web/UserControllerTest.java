package com.example.projeto_turismo.web;

import com.example.projeto_turismo.controllers.UserController;
import com.example.projeto_turismo.domains.Role;
import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.dto.UserDto;
import com.example.projeto_turismo.infra.security.SecurityFilter;
import com.example.projeto_turismo.services.AuthService;
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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.List;

import static com.example.projeto_turismo.common.UserConstants.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    @MockBean
    private AuthService authService;

    private final String urlPost = "/users";
    private final String urlGetId = "/users/me";
    private final String urlGetFindAll = "/users";


    @Test
    public void createUser_WithValidData_RetunrCreated() throws Exception {
        when(authService.registerUser(REGISTER_DTO)).thenReturn(USERENTITY);
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
        when(authService.registerUser(any())).thenThrow(DataIntegrityViolationException.class);

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
    @Test
    public void listUsers_ReturnsFilteredUsers() throws Exception {
        UserDto user = new UserDto (1L ,"Maxsuel", "922222", "max@gmail", Role.USER);
        List<UserDto> users = List.of(user);


        when(service.findAll()).thenReturn(users);

        mockMvc.perform(get(urlGetFindAll))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Maxsuel"));
    }
    @Test
    public void listUser_ReturnsNoUser() throws Exception {
        List<UserDto> users = Collections.emptyList();
        when(service.findAll()).thenReturn(users);

        mockMvc.perform(get(urlGetFindAll))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
    @Test
    public void removeUser_WithExistingId_ReturnsNoContent() throws Exception{
        mockMvc.perform(delete(urlGetId))
                .andExpect(status().isNoContent());
    }
}
