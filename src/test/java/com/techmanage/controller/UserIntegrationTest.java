package com.techmanage.controller;


import com.techmanage.model.User;
import com.techmanage.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void createUser_ShouldPersistInDatabase() throws Exception {
        String userJson = """
        {
            "fullName": "Integration Test",
            "email": "test@integration.com",
            "phone": "+55 11 98888-8888",
            "birthDate": "1990-01-01",
            "userType": "ADMIN"
        }
        """;

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated());

        User savedUser = userRepository.findByEmail("test@integration.com").orElseThrow();
        assertEquals("Integration Test", savedUser.getFullName());
    }
}
