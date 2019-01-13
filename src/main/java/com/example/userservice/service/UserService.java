package com.example.userservice.service;

import com.example.userservice.model.LoginCheckWrapper;
import com.example.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findUserById(String id);
    LoginCheckWrapper isLoginExist(String login);
    String saveUser(User user);
    String blockUser(String userId);
    User authenticationUser(User user);
}
