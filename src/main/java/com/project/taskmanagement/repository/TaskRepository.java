package com.project.taskmanagement.repository;

import com.project.taskmanagement.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    // Add custom query methods if needed
    List<TaskEntity> findByAssignedUsers_UserId(Long userId);
}
