package com.project.taskmanagement.service;

import com.project.taskmanagement.entity.TaskEntity;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    TaskEntity createTask(TaskEntity task);

    Optional<TaskEntity> getTaskById(Long taskId);

    List<TaskEntity> getAllTasks();

    List<TaskEntity> getAllTasksForUser(Long userId);

    TaskEntity updateTask(Long taskId, TaskEntity updatedTask);

    void deleteTask(Long taskId);

    boolean existsById(Long taskId);
}
