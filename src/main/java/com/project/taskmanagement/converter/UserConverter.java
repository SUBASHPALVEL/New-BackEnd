package com.project.taskmanagement.converter;

import com.github.dozermapper.core.Mapper;
import com.project.taskmanagement.dto.UserDTO;
import com.project.taskmanagement.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private Mapper dozerBeanMapper;

    public UserEntity convertToEntity(UserDTO userDTO) {
        return dozerBeanMapper.map(userDTO, UserEntity.class);
    }

    public UserDTO convertToDTO(UserEntity userEntity) {
        return dozerBeanMapper.map(userEntity, UserDTO.class);
    }
}
