package com.example.userservice.service.impl;

import com.example.userservice.model.LoginCheckWrapper;
import com.example.userservice.model.User;
import com.example.userservice.model.UserRole;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import com.sun.jersey.api.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findUserById(String id) {
        return repository.findById(id);
    }

    @Override
    public LoginCheckWrapper isLoginExist(String login) {
        return LoginCheckWrapper.builder()
                .isLoginExists(repository.existsByLogin(login))
                .build();
    }

    @Override
    public String saveUser(User user) {
        return repository.save(user).getId();
    }

    @Override
    public String blockUser(String userId) {
        User user = repository.findById(userId).orElseThrow(NotFoundException::new);
        if(user.getIsUserBlock()) {
            user.setIsUserBlock(false);
        } else {
            if (user.getUserRole().equals(UserRole.USER)) {
                user.setIsUserBlock(true);
            }
        }

        return saveUser(user);
    }

    @Override
    public User authenticationUser(User user) {
        String login = user.getLogin();
        String password = user.getPassword();

        return repository.findByLoginAndPassword(login, password).orElse(null);
    }
}
