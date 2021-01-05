package com.taskmanagement.service.impl;

import com.taskmanagement.model.Project;
import com.taskmanagement.repository.ProjectRepository;
import com.taskmanagement.service.ProjectService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@NoArgsConstructor
@Repository
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public void saveProject(Project project) {
        projectRepository.save(project);
    }
}
