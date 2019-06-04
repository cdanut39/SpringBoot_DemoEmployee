package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.UserDTO;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.user.UserAlreadyExistsException;

public interface UserService {

    UserDTO getUserById(int id) throws EmployeeNotFoundException;

    UserDTO save(UserDTO user) throws UserAlreadyExistsException;
}
