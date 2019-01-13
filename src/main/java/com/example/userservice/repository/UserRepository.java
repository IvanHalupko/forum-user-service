package com.example.userservice.repository;

import com.example.userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByLogin(String login);
    Optional<User> findByLoginAndPassword(String login, String password);
}
