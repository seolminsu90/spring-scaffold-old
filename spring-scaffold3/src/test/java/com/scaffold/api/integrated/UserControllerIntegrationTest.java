package com.scaffold.api.integrated;

import com.scaffold.api.entity.UserEntity;
import com.scaffold.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    @Transactional
    public void setUp() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("John Doe");
        user.setAge(27);
        userRepository.save(user);
    }

    @Test
    public void testGetUser() throws Exception {
        long userId = 1L;
        String expectedName = "John Doe";

        mockMvc.perform(get("/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(userId))
                .andExpect(jsonPath("$.data.name").value(expectedName));
    }

    @Test
    public void testGetUser2() throws Exception {
        long userId = 1L;
        String expectedName = "John Doe";

        mockMvc.perform(get("/users/{userId}/queryDsl", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(userId))
                .andExpect(jsonPath("$.data.name").value(expectedName));
    }
}
