package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.BaseEmployeeDTO;
import com.learning.spring.rest.employees.dto.EmployeePOSTReq_DTO;
import com.learning.spring.rest.employees.dto.EmployeePUTReq_DTO;
import com.learning.spring.rest.employees.dto.EmployeePUTResponse_DTO;
import com.learning.spring.rest.employees.exceptions.department.DepartmentNotFoundByIdException;
import com.learning.spring.rest.employees.exceptions.department.DepartmentNotFoundByNameException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;


public interface EmployeeService {

    BaseEmployeeDTO getEmployeeById(int id) throws EmployeeNotFoundException;

    BaseEmployeeDTO save(EmployeePOSTReq_DTO employee);

    EmployeePUTResponse_DTO updateEmployee(int id, EmployeePUTReq_DTO employee) throws EmployeeNotFoundException;

    BaseEmployeeDTO assignDepartment(int  employeeId, String deptName) throws EmployeeNotFoundException, DepartmentNotFoundByIdException, DepartmentNotFoundByNameException;


}
