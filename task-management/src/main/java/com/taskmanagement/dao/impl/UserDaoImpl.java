package com.taskmanagement.dao.impl;

import com.mongodb.client.result.UpdateResult;
import com.taskmanagement.dao.UserDao;
import com.taskmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    MongoTemplate mongoTemplate;

    final String COLLECTION = "user";

    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user, COLLECTION);
    }

    @Override
    public User findUserByEmail(String email) {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(
                Criteria.where("email").is(email)));
        User currentUser = mongoTemplate.findOne(query, User.class);
        return currentUser;
    }

    @Override
    public User findUserByEmailAndPassword(User user) {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(
                Criteria.where("email").is(user.getEmail()),
                Criteria.where("password").is(user.getPassword())));
        User currentUser = mongoTemplate.findOne(query, User.class);
        return currentUser;
    }

}
