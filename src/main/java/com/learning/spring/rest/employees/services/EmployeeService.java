package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.dto.UserDTO;
import com.learning.spring.rest.employees.exceptions.custom.NoResultsException;
import com.learning.spring.rest.employees.exceptions.custom.community.CommunityNotFoundByIdException;
import com.learning.spring.rest.employees.exceptions.custom.community.CommunityNotFoundByNameException;
import com.learning.spring.rest.employees.exceptions.custom.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.custom.user.UserAlreadyExistsException;

import java.util.List;


public interface EmployeeService {

    EmployeeDTO getEmployeeById(int id) throws EmployeeNotFoundException;

    EmployeeDTO save(EmployeeDTO employee) throws UserAlreadyExistsException, CommunityNotFoundByNameException;

    EmployeeDTO updateEmployee(int id, EmployeeDTO employee) throws EmployeeNotFoundException;

    UserDTO assignCommunity(int employeeId, BaseCommunityDTO community) throws EmployeeNotFoundException, CommunityNotFoundByIdException, CommunityNotFoundByNameException;

    void removeEmployee(int id) throws EmployeeNotFoundException;

    List<EmployeeDTO> getAllEmployees();

    List<EmployeeDTO> getEmployeesSortedByCriteria(String criteria, String direction);

    List<EmployeeDTO> searchEmployeeBy(String lastName, String community) throws NoResultsException;

    List<EmployeeDTO> getEmployeesWithPagination(int page, int size, String criteria);
}
