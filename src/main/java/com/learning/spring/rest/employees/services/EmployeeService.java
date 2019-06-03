package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.*;
import com.learning.spring.rest.employees.exceptions.department.DepartmentNotFoundByIdException;
import com.learning.spring.rest.employees.exceptions.department.DepartmentNotFoundByNameException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;


public interface EmployeeService {

//    UserDTO getEmployeeById(int id) throws EmployeeNotFoundException;

    UserDTO save(EmployeeDTO employee);

//    EmployeePUTResponse_DTO updateEmployee(int id, EmployeePUTReq_DTO employee) throws EmployeeNotFoundException;
//
//    UserDTO assignDepartment(int  employeeId, String deptName) throws EmployeeNotFoundException, DepartmentNotFoundByIdException, DepartmentNotFoundByNameException;


}
