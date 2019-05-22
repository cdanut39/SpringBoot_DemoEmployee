package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.DepartmentRepo;
import com.learning.spring.rest.employees.dao.EmployeeRepo;
import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.exceptions.EmployeeNotFoundException;
import com.learning.spring.rest.employees.mappers.EmployeeMapper;
import com.learning.spring.rest.employees.model.Department;
import com.learning.spring.rest.employees.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.learning.spring.rest.employees.utils.DateAndTimeUtils.getCurrentDate;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepo employeeRepo;
    private DepartmentRepo departmentRepo;
    private EmployeeMapper employeeMapper;
    private DepartmentServiceImpl departmentService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo, DepartmentRepo departmentRepo, EmployeeMapper employeeMapper, DepartmentServiceImpl departmentService) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
        this.employeeMapper = employeeMapper;
        this.departmentService = departmentService;
    }


    public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id=" + id, id));
        employee.setDeptName(employee);
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
        employee.setStartDate(getCurrentDate());
        Employee savedEmp = employeeRepo.save(employee);
        EmployeeDTO employeeDTO = employeeMapper.convertFromEmpToEmpDto(savedEmp);
        logger.info("Employee with id {} and name {} was added successfully!", employee.getId(), employee.getName());

        return employeeDTO;

    }

    public EmployeeDTO updateEmployee(int id, Employee emp) {
        Employee employeeToBeUpdated = employeeRepo.getOne(id);
        employeeToBeUpdated.setName(emp.getName());
        employeeToBeUpdated.setSalary(emp.getSalary());
        employeeToBeUpdated.setBonus(emp.isBonus());

        String deptName = emp.getDepartment().getDeptName();
        Department department = departmentRepo.findByDeptName(deptName);
        if (department != null) {
            employeeToBeUpdated.setDepartment(department);
        } else {
            employeeToBeUpdated.setDepartment(emp.getDepartment());
        }

        employeeRepo.save(employeeToBeUpdated);
        EmployeeDTO employeeDTO = employeeMapper.convertFromEmpToEmpDto(employeeToBeUpdated);
        logger.info("Details of employee with id:{} were successfully updated!", id);
        return employeeDTO;
    }
}
