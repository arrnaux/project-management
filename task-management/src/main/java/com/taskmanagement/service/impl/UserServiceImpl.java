package com.taskmanagement.service.impl;

import com.taskmanagement.model.User;
import com.taskmanagement.repository.UserRepository;
import com.taskmanagement.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean isEmailFree(String email) {
        List<User> userList = userRepository.findByEmail(email);
        return userList.isEmpty();
    }

    @Override
    public User userLogin(User user) {
        List<User> userList = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if(!userList.isEmpty()) {
            return userList.get(0);
        }
        return null;
    }
}
