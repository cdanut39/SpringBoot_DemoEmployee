package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.BaseEmployeeDTO;
import com.learning.spring.rest.employees.exceptions.EmployeeNotFoundException;
import com.learning.spring.rest.employees.model.Employee;


public interface EmployeeService {

    BaseEmployeeDTO getEmployeeById(int id) throws EmployeeNotFoundException;

    BaseEmployeeDTO save(Employee employee);

    BaseEmployeeDTO updateEmployee(int id, Employee employee);


}
