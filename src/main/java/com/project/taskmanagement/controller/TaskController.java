package com.project.taskmanagement.controller;

import com.project.taskmanagement.dto.TaskDTO;
import com.project.taskmanagement.converter.TaskConverter;
import com.project.taskmanagement.entity.TaskEntity;
import com.project.taskmanagement.exception.ResourceNotFoundException;
import com.project.taskmanagement.service.TaskService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskConverter taskConverter;

    @Autowired
    public TaskController(TaskService taskService, TaskConverter taskConverter) {
        this.taskService = taskService;
        this.taskConverter = taskConverter;
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid TaskDTO taskDTO) {
        TaskEntity createdTask = taskService.createTask(taskConverter.convertToEntity(taskDTO));
        return new ResponseEntity<>(taskConverter.convertToDTO(createdTask), HttpStatus.CREATED);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long taskId) {
        TaskEntity taskEntity = taskService.getTaskById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        return new ResponseEntity<>(taskConverter.convertToDTO(taskEntity), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> tasks = taskService.getAllTasks().stream()
                .map(taskConverter::convertToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskDTO>> getAllTasksForUser(@PathVariable Long userId) {
        List<TaskDTO> tasksForUser = taskService.getAllTasksForUser(userId).stream()
                .map(taskConverter::convertToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(tasksForUser, HttpStatus.OK);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId, @RequestBody @Valid TaskDTO taskDTO) {
        if (!taskService.existsById(taskId)) {
            throw new ResourceNotFoundException("Task not found with id: " + taskId);
        }

        TaskEntity updatedTask = taskService.updateTask(taskId, taskConverter.convertToEntity(taskDTO));
        return new ResponseEntity<>(taskConverter.convertToDTO(updatedTask), HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        if (!taskService.existsById(taskId)) {
            throw new ResourceNotFoundException("Task not found with id: " + taskId);
        }

        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
