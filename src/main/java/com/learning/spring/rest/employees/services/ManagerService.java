package com.learning.spring.rest.employees.services;


import com.learning.spring.rest.employees.dto.ManagerDTO;
import com.learning.spring.rest.employees.exceptions.custom.manager.ManagerNotFoundException;
import com.learning.spring.rest.employees.exceptions.custom.user.UserAlreadyExistsException;

public interface ManagerService {

    ManagerDTO save(ManagerDTO managerDTO) throws UserAlreadyExistsException;

    ManagerDTO findManagerByName(String firstName, String lastName) throws ManagerNotFoundException;
}
