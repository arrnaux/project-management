package com.taskmanagement.dao;

import com.taskmanagement.model.User;

public interface UserDao {
    void saveUser(User user);

    User findUserByEmail(String email);

    User findUserByEmailAndPassword(User user);
}
