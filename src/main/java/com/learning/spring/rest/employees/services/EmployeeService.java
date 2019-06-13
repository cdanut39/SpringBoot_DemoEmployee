package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.dto.UserDTO;
import com.learning.spring.rest.employees.exceptions.community.CommunityNotFoundByIdException;
import com.learning.spring.rest.employees.exceptions.community.CommunityNotFoundByNameException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.user.UserAlreadyExistsException;

import java.util.List;


public interface EmployeeService {

    EmployeeDTO getEmployeeById(int id) throws EmployeeNotFoundException;

    EmployeeDTO save(EmployeeDTO employee) throws UserAlreadyExistsException;

    //    EmployeePUTResponse_DTO updateEmployee(int id, EmployeePUTReq_DTO employee) throws EmployeeNotFoundException;
//
    UserDTO assignCommunity(int employeeId, BaseCommunityDTO Community) throws EmployeeNotFoundException, CommunityNotFoundByIdException, CommunityNotFoundByNameException;

    void removeEmployee(int id) throws EmployeeNotFoundException;

    List<EmployeeDTO> getAllEmployees();

    List<EmployeeDTO> getEmployeesSortedByCriteria(String criteria, String direction);
}
