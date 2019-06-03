package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.BaseDepartmentDTO;
import com.learning.spring.rest.employees.dto.DepartmentDTO;
import com.learning.spring.rest.employees.exceptions.department.DefaultDepartmentCanNotBeRemovedException;
import com.learning.spring.rest.employees.exceptions.department.DepartmentAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.department.DepartmentNotFoundByIdException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.model.Department;

import java.util.List;


public interface DepartmentService {

    BaseDepartmentDTO addDepartment(Department department) throws DepartmentAlreadyExistsException;

    void deleteDepartmentById(int id) throws DepartmentNotFoundByIdException, DefaultDepartmentCanNotBeRemovedException;

    DepartmentDTO getDepartmentById(int id) throws EmployeeNotFoundException, DepartmentNotFoundByIdException;

    Department getDefaultDepartment(int id);

    List<DepartmentDTO> getAllDepartments();

}
