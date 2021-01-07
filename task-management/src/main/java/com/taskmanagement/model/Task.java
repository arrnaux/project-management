package com.taskmanagement.model;

import com.taskmanagement.types.TaskPriority;
import com.taskmanagement.types.TaskStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
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
