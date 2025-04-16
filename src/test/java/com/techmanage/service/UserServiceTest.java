package com.techmanage.service;

import com.techmanage.dto.UserDTO;
import com.techmanage.exception.ApiException;
import com.techmanage.exception.NotFoundException;
import com.techmanage.model.User;
import com.techmanage.model.UserType;
import com.techmanage.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserDTO createValidUserDTO() {
        UserDTO dto = new UserDTO();
        dto.setFullName("John Doe");
        dto.setEmail("john@example.com");
        dto.setPhone("+55 11 99999-9999");
        dto.setBirthDate(java.sql.Date.valueOf(LocalDate.of(1990, 1, 1)));
        dto.setUserType(UserType.ADMIN);
        return dto;
    }

    private User createValidUser() {
        User user = new User();
        user.setId(1L);
        user.setFullName("John Doe");
        user.setEmail("john@example.com");
        user.setPhone("+55 11 99999-9999");
        user.setBirthDate(java.sql.Date.valueOf(LocalDate.of(1990, 1, 1)));
        user.setUserType(UserType.ADMIN);
        return user;
    }

    @Test
    void createUser_WithValidData_ReturnsUser() {
        UserDTO userDTO = createValidUserDTO();
        User expectedUser = createValidUser();

        when(userRepository.existsByEmail(userDTO.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        UserDTO createdUser = userService.createUser(userDTO);

        assertNotNull(createdUser);
        assertEquals(expectedUser.getEmail(), createdUser.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void createUser_WithDuplicateEmail_ThrowsException() {
        UserDTO userDTO = createValidUserDTO();

        when(userRepository.existsByEmail(userDTO.getEmail())).thenReturn(true);

        assertThrows(ApiException.class, () -> {
            userService.createUser(userDTO);
        });

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void getUserById_WithExistingId_ReturnsUser() {
        Long userId = 1L;
        User expectedUser = createValidUser();

        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        UserDTO foundUser = userService.getUserById(userId);

        assertNotNull(foundUser);
        assertEquals(expectedUser.getId(), foundUser.getId());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void getUserById_WithNonExistingId_ThrowsException() {
        Long userId = 99L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            userService.getUserById(userId);
        });

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void updateUser_WithValidData_ReturnsUpdatedUser() {
        Long userId = 1L;
        User existingUser = createValidUser();
        UserDTO updateDTO = createValidUserDTO();
        updateDTO.setFullName("Updated Name");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserDTO updatedUser = userService.updateUser(userId, updateDTO);

        assertEquals("Updated Name", updatedUser.getFullName());
        verify(userRepository, times(1)).save(existingUser);
    }

}