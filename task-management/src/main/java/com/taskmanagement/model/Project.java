package com.taskmanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@NoArgsConstructor
@Data
@Document(collection = "project")
public class Project {
    @Id
    private String id;
    private String name;
    private String description;
    private Set<String> usersId;
    private Set<String> tasksId;
}
