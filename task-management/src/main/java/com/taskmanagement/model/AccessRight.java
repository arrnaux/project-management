package com.taskmanagement.model;

import com.taskmanagement.types.Right;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name="access_right")
public class AccessRight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToMany(mappedBy = "rights", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Project> projects = new HashSet<>();

    private Right right;
}
