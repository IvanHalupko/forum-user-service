package com.example.userservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

@Document(collection = "users_collection")
@Data
public class User {

    @Id
    private String id;

    @Size(max = 128)
    private String userName;

    @Size(max = 128)
    private String lastName;

    @Size(max = 64)
    private String login;

    @Size(max = 64)
    private String password;

    private UserRole userRole;

    private Boolean isUserBlock;
}
