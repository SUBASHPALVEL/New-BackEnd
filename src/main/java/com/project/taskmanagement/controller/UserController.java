package com.project.taskmanagement.controller;

import com.project.taskmanagement.dto.UserDTO;
import com.project.taskmanagement.converter.UserConverter;
import com.project.taskmanagement.entity.UserEntity;
import com.project.taskmanagement.exception.ResourceNotFoundException;
import com.project.taskmanagement.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    private UserConverter userConverter;

    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
        UserEntity createdUser = userService.createUser(userConverter.convertToEntity(userDTO));
        return new ResponseEntity<>(userConverter.convertToDTO(createdUser), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        UserEntity userEntity = userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return new ResponseEntity<>(userConverter.convertToDTO(userEntity), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers().stream()
                .map(userConverter::convertToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody @Valid UserDTO userDTO) {
        if (!userService.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        UserEntity updatedUser = userService.updateUser(userId, userConverter.convertToEntity(userDTO));
        return new ResponseEntity<>(userConverter.convertToDTO(updatedUser), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        if (!userService.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
