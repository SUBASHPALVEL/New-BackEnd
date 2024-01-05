package com.project.taskmanagement.converter;

import com.github.dozermapper.core.Mapper;
import com.project.taskmanagement.dto.TaskDTO;
import com.project.taskmanagement.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {

    @Autowired
    private Mapper dozerBeanMapper;

    public TaskEntity convertToEntity(TaskDTO taskDTO) {
        return dozerBeanMapper.map(taskDTO,TaskEntity.class);
    }

    public TaskDTO convertToDTO(TaskEntity taskEntity) {
        return dozerBeanMapper.map(taskEntity,TaskDTO.class);
    }
}
