package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.*;
import com.learning.spring.rest.employees.exceptions.department.DepartmentNotFoundByIdException;
import com.learning.spring.rest.employees.exceptions.department.DepartmentNotFoundByNameException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.user.UserAlreadyExistsException;


public interface EmployeeService {

    EmployeeDTO getEmployeeById(int id) throws EmployeeNotFoundException;

    EmployeeDTO save(EmployeeDTO employee) throws UserAlreadyExistsException;

//    EmployeePUTResponse_DTO updateEmployee(int id, EmployeePUTReq_DTO employee) throws EmployeeNotFoundException;
//
//    UserDTO assignDepartment(int  employeeId, String deptName) throws EmployeeNotFoundException, DepartmentNotFoundByIdException, DepartmentNotFoundByNameException;


}
