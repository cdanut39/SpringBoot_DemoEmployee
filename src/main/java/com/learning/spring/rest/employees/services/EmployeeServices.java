package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.DepartmentRepo;
import com.learning.spring.rest.employees.dao.EmployeeRepo;
import com.learning.spring.rest.employees.exceptions.EmployeeNotFoundException;
import com.learning.spring.rest.employees.model.Department;
import com.learning.spring.rest.employees.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EmployeeServices {

    private static final Logger logger = LogManager.getLogger(EmployeeServices.class);

    private EmployeeRepo employeeRepo;
    private DepartmentRepo departmentRepo;


    @Autowired
    public EmployeeServices(EmployeeRepo employeeRepo, DepartmentRepo departmentRepo) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
    }

//    public Employee convertFromEmpDtoToEmp(EmployeeDTO dto) {
//
//        Employee e = new Employee();
//        e.setId(dto.getId());
//        e.setDepartment(dto.getDepartment());
//        e.setName(dto.getName());
//        return e;
//    }
//
//    EmployeeDTO convertFromEmpToEmpDto(Employee emp) {
//
//        EmployeeDTO dto = new EmployeeDTO();
//        dto.setDepartment(emp.getDept());
//        dto.setSalary(emp.getSalary);
//
//        return dto;
//
//    }

    public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id=" + id, id));
        logger.info("Information for employee with id=" + id + ": Name={}, Salary={}", employee.getName(), employee.getSalary());
        return employee;
    }


    @Transactional
    public Employee save(Employee employee) {

//        Employee employee = convertFromEmpDtoToEmp(employeeDTO);
        String deptName = employee.getDepartment().getDeptName();
        Department department = departmentRepo.findByDeptName(deptName);

        if (department != null) {
            employee.setDepartment(department);
//            employee.getDepartment().setDeptId(department.getDeptId());
        }

        Employee savedEmp = employeeRepo.save(employee);
        logger.info("Employee with id {} and name {} was added successfully!", employee.getId(), employee.getName());

        return savedEmp;

    }
}
