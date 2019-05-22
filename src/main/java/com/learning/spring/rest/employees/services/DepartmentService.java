package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.DepartmentDTO;
import com.learning.spring.rest.employees.exceptions.DepartmentNotFoundException;
import com.learning.spring.rest.employees.exceptions.EmployeeNotFoundException;
import com.learning.spring.rest.employees.model.Department;


public interface DepartmentService {

    DepartmentDTO addDepartment(Department department);

    void deleteDepartmentById(int id) throws DepartmentNotFoundException;

    DepartmentDTO getDepartmentById(int id) throws EmployeeNotFoundException, DepartmentNotFoundException;

}
