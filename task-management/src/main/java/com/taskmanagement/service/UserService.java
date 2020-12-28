package com.taskmanagement.service;

import com.taskmanagement.model.User;

public interface UserService {
    void createUser(User user);

    boolean isEmailFree(String email);

    User userLogin(String email, String password);
}
