package com.taskmanagement.dao;

import com.taskmanagement.model.Task;

import java.util.List;

public interface TaskDao {
    void saveTask(Task task);

    List<Task> findTaskByUser(String userId);

    List<Task> findTaskByProject(String projectId);

    Task findTaskById(String id);

    void deleteTaskByProject(String projectId);

    void deleteTaskById(String id);
}
