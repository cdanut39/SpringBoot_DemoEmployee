package com.learning.spring.rest.employees.dto;

import lombok.Data;

@Data
public class PasswordDTO {
    private String password;
    private String confirmPassword;
}
