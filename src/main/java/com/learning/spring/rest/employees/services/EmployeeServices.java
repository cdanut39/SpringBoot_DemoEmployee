package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.mappers.EmployeeMapper;
import com.learning.spring.rest.employees.dao.DepartmentRepo;
import com.learning.spring.rest.employees.dao.EmployeeRepo;
import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.exceptions.EmployeeNotFoundException;
import com.learning.spring.rest.employees.model.Department;
import com.learning.spring.rest.employees.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class EmployeeServices {

    private static final Logger logger = LogManager.getLogger(EmployeeServices.class);

    private EmployeeRepo employeeRepo;
    private DepartmentRepo departmentRepo;
    private EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServices(EmployeeRepo employeeRepo, DepartmentRepo departmentRepo, EmployeeMapper employeeMapper) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
        this.employeeMapper = employeeMapper;
    }


    public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id=" + id, id));
        logger.info("Information for employee with id=" + id + ": Name={}, Salary={}", employee.getName(), employee.getSalary());
        return employee;
    }


    @Transactional
    public EmployeeDTO save(Employee employee) {

        String deptName = employee.getDepartment().getDeptName();
        Department department = departmentRepo.findByDeptName(deptName);

        if (department != null) {
            employee.setDepartment(department);
        }
        employee.setStartDate(LocalDate.now());
        Employee savedEmp = employeeRepo.save(employee);
        EmployeeDTO employeeDTO = employeeMapper.convertFromEmpToEmpDto(savedEmp);
        logger.info("Employee with id {} and name {} was added successfully!", employee.getId(), employee.getName());

        return employeeDTO;

    }
}
