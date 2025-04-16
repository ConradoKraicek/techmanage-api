package com.techmanage.service;


import com.techmanage.dto.UserDTO;
import com.techmanage.exception.ApiException;
import com.techmanage.exception.NotFoundException;
import com.techmanage.model.User;
import com.techmanage.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository, UserDTO userDTO) {
        this.userRepository = userRepository;
    }


    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ApiException("Email já está em uso");
        }

        User user = userDTO.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userDTO.toDTO(savedUser);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado com o ID: " + id));
        return UserDTO.toDTO(user);
    }

    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado com o ID: " + id));

        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setBirthDate(userDTO.getBirthDate());
        user.setUserType(userDTO.getUserType());

        User updatedUser = userRepository.save(user);
        return userDTO.toDTO(updatedUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Usuário não encontrado com o ID: " + id);
        }
        userRepository.deleteById(id);
    }
}
