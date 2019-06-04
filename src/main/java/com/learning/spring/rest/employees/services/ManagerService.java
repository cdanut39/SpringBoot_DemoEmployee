package com.learning.spring.rest.employees.services;


import com.learning.spring.rest.employees.dto.ManagerDTO;
import com.learning.spring.rest.employees.exceptions.user.UserAlreadyExistsException;
import com.learning.spring.rest.employees.model.Manager;

public interface ManagerService {
    Manager getDefaultManager(int id);

    ManagerDTO save(ManagerDTO managerDTO) throws UserAlreadyExistsException;
}
