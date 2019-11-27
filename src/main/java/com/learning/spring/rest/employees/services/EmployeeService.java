package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.dto.PasswordDTO;
import com.learning.spring.rest.employees.dto.UserDTO;
import com.learning.spring.rest.employees.exceptions.ExpiredTokenException;
import com.learning.spring.rest.employees.exceptions.PasswordMismatchException;
import com.learning.spring.rest.employees.exceptions.custom.NoResultsException;
import com.learning.spring.rest.employees.exceptions.custom.community.CommunityNotFoundByIdException;
import com.learning.spring.rest.employees.exceptions.custom.community.CommunityNotFoundByNameException;
import com.learning.spring.rest.employees.exceptions.custom.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.custom.manager.ManagerNotFoundException;
import com.learning.spring.rest.employees.exceptions.custom.user.UserAlreadyExistsException;

import java.util.HashMap;
import java.util.List;


public interface EmployeeService {

    EmployeeDTO registerEmployee(EmployeeDTO employee) throws UserAlreadyExistsException, CommunityNotFoundByNameException, ManagerNotFoundException;

    EmployeeDTO setEmployeePassword(String token, PasswordDTO passwordDTO) throws EmployeeNotFoundException, ExpiredTokenException, PasswordMismatchException;

    EmployeeDTO updateEmployee(int id, EmployeeDTO employee) throws EmployeeNotFoundException;

    UserDTO assignCommunity(int employeeId, BaseCommunityDTO community) throws EmployeeNotFoundException, CommunityNotFoundByIdException, CommunityNotFoundByNameException;

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(int id) throws EmployeeNotFoundException;

    List<EmployeeDTO> getEmployeesSortedByCriteria(String criteria, String direction);

    List<EmployeeDTO> searchEmployeeBy(String lastName, String community) throws NoResultsException;

    HashMap<String, Object> getEmployeesWithPagination(int page, int size, String criteria);

    void removeEmployee(int id) throws EmployeeNotFoundException;
}
