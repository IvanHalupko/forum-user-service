package com.example.userservice.controller;

import com.example.userservice.model.LoginCheckWrapper;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import com.sun.jersey.api.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") String id) {
        return userService.findUserById(id)
                .orElseThrow(() -> new NotFoundException("User with id '" + id + "' not found"));
    }

    @GetMapping("/check-login/{login}")
    public LoginCheckWrapper isLoginExists(@PathVariable("login") String login) {
        return userService.isLoginExist(login);
    }

    @PostMapping("user")
    public String saveUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("auth-user")
    public User authenticationUser(@RequestBody User user) {
        return userService.authenticationUser(user);
    }

    @GetMapping("block-user/{userId}")
    public String blockUser(@PathVariable("userId") String userId) {
        return userService.blockUser(userId);
    }
}
