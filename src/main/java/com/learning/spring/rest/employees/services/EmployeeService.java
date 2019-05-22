package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.exceptions.EmployeeNotFoundException;
import com.learning.spring.rest.employees.model.Employee;


public interface EmployeeService {

    Employee getEmployeeById(int id) throws EmployeeNotFoundException;

    EmployeeDTO save(Employee employee);

    EmployeeDTO updateEmployee(int id, Employee employee);


}
