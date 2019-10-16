package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.UserDTO;
import com.learning.spring.rest.employees.exceptions.custom.user.UserNotFoundException;

public interface UserService {

    UserDTO getUserById(int id) throws UserNotFoundException;

    void removeUser(int id) throws UserNotFoundException;
}
