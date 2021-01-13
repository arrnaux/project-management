package com.taskmanagement.dao.impl;

import com.taskmanagement.dao.TaskDao;
import com.taskmanagement.model.Project;
import com.taskmanagement.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDaoImpl implements TaskDao {

    @Autowired
    MongoTemplate mongoTemplate;

    final String COLLECTION = "task";

    @Override
    public void saveTask(Task task) {
        mongoTemplate.save(task, COLLECTION);
    }

    @Override
    public List<Task> findTaskByUser(String userId) {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(
                Criteria.where("usersId").all(userId)));
        return mongoTemplate.find(query, Task.class);
    }

    @Override
    public List<Task> findTaskByProject(String projectId) {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(
                Criteria.where("projectId").is(projectId)));
        return mongoTemplate.find(query, Task.class);
    }

    @Override
    public Task findTaskById(String id) {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(
                Criteria.where("id").is(id)));
        return mongoTemplate.findOne(query, Task.class);
    }

    @Override
    public void deleteTaskByProject(String projectId) {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(
                Criteria.where("projectId").is(projectId)));
        mongoTemplate.remove(query, Task.class);
    }

    @Override
    public void deleteTaskById(String id) {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(
                Criteria.where("id").is(id)));
        mongoTemplate.remove(query, Task.class);
    }

}
