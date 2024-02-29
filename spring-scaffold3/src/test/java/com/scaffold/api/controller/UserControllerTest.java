package com.scaffold.api.controller;

import com.scaffold.api.dto.UserDTO;
import com.scaffold.api.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserDTO mockResultDTO(long userId, String name) {
        return new UserDTO(userId, name, 27);
    }

    @Test
    public void testGetUser() throws Exception {
        long userId = 1L;
        String name = "John Doe";
        UserDTO userDTO = mockResultDTO(userId, name);

        when(userService.getUserByJpa(userId)).thenReturn(userDTO);

        mockMvc.perform(get("/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(userId))
                .andExpect(jsonPath("$.data.name").value(name));

        verify(userService, times(1)).getUserByJpa(userId);
    }

    @Test
    public void testGetUser2() throws Exception {
        long userId = 1L;
        String name = "John Doe";
        UserDTO userDTO = mockResultDTO(userId, name);

        when(userService.getUserByQueryDSL(userId)).thenReturn(userDTO);

        mockMvc.perform(get("/users/{userId}/queryDsl", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(userId))
                .andExpect(jsonPath("$.data.name").value(name));

        verify(userService, times(1)).getUserByQueryDSL(userId);
    }
}