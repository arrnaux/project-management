package com.taskmanagement.dao;

import com.taskmanagement.model.Project;

public interface ProjectDao {
    void insertProject(Project project);

    Project findProjectByUser(String userId, String name, String description);
}
