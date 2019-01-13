package com.example.userservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginCheckWrapper {
    private Boolean isLoginExists;
}
