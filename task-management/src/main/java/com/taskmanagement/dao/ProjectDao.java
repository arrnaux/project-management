package com.taskmanagement.dao;

import com.taskmanagement.model.Project;

import java.util.List;

public interface ProjectDao {
    void saveProject(Project project);

    List<Project> findProjectsByUser(String userId);

    Project findProjectById(String id);
}
