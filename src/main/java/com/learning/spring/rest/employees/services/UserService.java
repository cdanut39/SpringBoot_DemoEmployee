package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.UserDTO;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.model.User;

public interface UserService {


    UserDTO getEmployeeById(int id) throws EmployeeNotFoundException;

    <T extends UserDTO> UserDTO save(T user);
}
