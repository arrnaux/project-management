package com.taskmanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@NoArgsConstructor
@Data
@Document(collection = "task")
public class Task {
    @Id
    private String id;
    private String name;
    private String description;
    private String status;
    private String priority;
    private String projectId;
    private Set<String> usersId;
}
