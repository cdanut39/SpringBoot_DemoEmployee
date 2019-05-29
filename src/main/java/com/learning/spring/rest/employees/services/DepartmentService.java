package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.BaseDepartmentDTO;
import com.learning.spring.rest.employees.dto.DepartmentDTO;
import com.learning.spring.rest.employees.exceptions.department.DepartmentAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.department.DepartmentNotFoundException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.model.Department;


public interface DepartmentService {

    BaseDepartmentDTO addDepartment(Department department) throws DepartmentAlreadyExistsException;

    void deleteDepartmentById(int id) throws DepartmentNotFoundException;

    DepartmentDTO getDepartmentById(int id) throws EmployeeNotFoundException, DepartmentNotFoundException;

}
