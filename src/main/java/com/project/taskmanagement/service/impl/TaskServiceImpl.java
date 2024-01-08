package com.project.taskmanagement.service.impl;

import com.github.dozermapper.core.Mapper;
import com.project.taskmanagement.converter.TaskConverter;
import com.project.taskmanagement.converter.UserConverter;
import com.project.taskmanagement.dto.TaskDTO;
import com.project.taskmanagement.entity.TaskEntity;
import com.project.taskmanagement.exception.ResourceNotFoundException;
import com.project.taskmanagement.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.taskmanagement.service.TaskService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskConverter taskConverter;

    @Autowired
    private Mapper dozerMapper;

    // public TaskServiceImpl(TaskRepository taskRepository) {
    // this.taskRepository = taskRepository;
    // }

    @Override
    public TaskEntity createTask(TaskEntity task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<TaskEntity> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public List<TaskEntity> getAllTasks() {

        return taskRepository.findAll();
    }

    @Override
    public List<TaskEntity> getAllTasksForUser(Long userId) {
        // Assuming your TaskEntity has a Many-to-Many relationship with UserEntity
        List<TaskEntity> userTasks = null;
        userTasks = taskRepository.findByAssignedUsers_UserId(userId);
        List<TaskDTO> userTasksDTO = userTasks.stream().map(userTask -> dozerMapper.map(userTask, TaskDTO.class)).collect(Collectors.toList());
        return taskRepository.findByAssignedUsers_UserId(userId);
    }

    @Override
    public TaskEntity updateTask(Long taskId, TaskEntity updatedTask) {
        if (!taskRepository.existsById(taskId)) {
            throw new ResourceNotFoundException("Task not found with id: " + taskId);
        }

        updatedTask.setTaskId(taskId);
        return taskRepository.save(updatedTask);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public boolean existsById(Long taskId) {
        return taskRepository.existsById(taskId);
    }
}
