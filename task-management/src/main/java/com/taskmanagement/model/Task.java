package com.taskmanagement.model;

import com.taskmanagement.types.TaskPriority;
import com.taskmanagement.types.TaskStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private TaskStatus status;

    private TaskPriority priority;

    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    private Project project;
}
