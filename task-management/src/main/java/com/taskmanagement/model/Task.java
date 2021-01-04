package com.taskmanagement.model;

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

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User owner;

    private TaskStatus status;

    private Date creationDate;

    private Date closeDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "task_label",
            joinColumns = {@JoinColumn(name = "task_id")},
            inverseJoinColumns = {@JoinColumn(name = "label_id")}
    )
    private Set<Label> labels = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    private Project project;

    @OneToMany(mappedBy="task")
    private Set<Comment> comments;
}
