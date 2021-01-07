package com.taskmanagement.dao.impl;

import com.taskmanagement.dao.UserDao;
import com.taskmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

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
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public User findUserByEmailAndPassword(User user) {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(
                Criteria.where("email").is(user.getEmail()),
                Criteria.where("password").is(user.getPassword())));
        return mongoTemplate.findOne(query, User.class);
    }

}
