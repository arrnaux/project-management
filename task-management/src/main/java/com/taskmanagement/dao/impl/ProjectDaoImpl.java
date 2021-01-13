package com.taskmanagement.dao.impl;

import com.taskmanagement.dao.ProjectDao;
import com.taskmanagement.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    MongoTemplate mongoTemplate;

    final String COLLECTION = "project";

    @Override
    public void saveProject(Project project) {
        mongoTemplate.save(project, COLLECTION);
    }

    @Override
    public List<Project> findProjectsByUser(String userId) {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(
                Criteria.where("usersId").all(userId)));
        return mongoTemplate.find(query, Project.class);
    }

    @Override
    public Project findProjectById(String id) {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(
                Criteria.where("id").is(id)));
        return mongoTemplate.findOne(query, Project.class);
    }

    @Override
    public void deleteProject(String id) {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(
                Criteria.where("id").is(id)));
        mongoTemplate.remove(query, Project.class);
    }
}
