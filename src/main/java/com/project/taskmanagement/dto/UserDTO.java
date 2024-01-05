package com.project.taskmanagement.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    
    private Long userId;

    private String username;

    @NotNull(message = "User Email is mandatory")
    @NotEmpty(message = "User Email cannot be empty")
    @Size(min = 1, max = 50, message = "Owner Email should be between 1 to 50 characters in length")
    private String usermail;

    @NotNull(message = "User Password is mandatory")
    @NotEmpty(message = "User Password cannot be empty")
    @Size(min = 5, max = 12, message = "User Password should be between 5 to 12 characters in length")
    private String password;

    private List<TaskDTO> assignedTasks;
    
}