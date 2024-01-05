package com.project.taskmanagement.service;

import com.project.taskmanagement.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserEntity createUser(UserEntity user);

    Optional<UserEntity> getUserById(Long userId);

    List<UserEntity> getAllUsers();

    UserEntity updateUser(Long userId, UserEntity updatedUser);

    void deleteUser(Long userId);

    boolean existsById(Long userId);
}
