package com.taskmanagement.dao.impl;

import com.taskmanagement.dao.ProjectDao;
import com.taskmanagement.model.Project;
import com.taskmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    MongoTemplate mongoTemplate;

    final String COLLECTION = "project";

    @Override
    public void insertProject(Project project) {
        mongoTemplate.insert(project, COLLECTION);
    }

    @Override
    public Project findProjectByUser(String userId, String name, String description) {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(
                Criteria.where("name").is(name),
                Criteria.where("description").is(description),
                Criteria.where(userId).in("usersId")));
        Project currentProject = mongoTemplate.findOne(query, Project.class);
        return currentProject;
    }
}
