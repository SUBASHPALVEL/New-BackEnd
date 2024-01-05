package com.project.taskmanagement.service.impl;

import com.project.taskmanagement.entity.TaskEntity;
import com.project.taskmanagement.exception.ResourceNotFoundException;
import com.project.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.taskmanagement.service.TaskService;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

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
