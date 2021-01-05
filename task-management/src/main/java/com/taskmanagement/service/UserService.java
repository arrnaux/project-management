package com.taskmanagement.service;

import com.taskmanagement.model.User;

public interface UserService {
    void saveUser(User user);

    boolean isEmailFree(String email);

    User userLogin(User user);
}
