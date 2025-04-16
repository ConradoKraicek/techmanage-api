package com.techmanage.config;

import com.techmanage.dto.UserDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserDTO userDTO() {
        return new UserDTO();
    }
}