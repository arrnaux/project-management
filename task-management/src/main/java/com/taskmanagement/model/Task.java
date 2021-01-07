package com.taskmanagement.model;

import com.taskmanagement.types.TaskPriority;
import com.taskmanagement.types.TaskStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@Document(collection = "task")
public class Task {
    @Id
    private String id;
    private String name;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private Project project;
}
