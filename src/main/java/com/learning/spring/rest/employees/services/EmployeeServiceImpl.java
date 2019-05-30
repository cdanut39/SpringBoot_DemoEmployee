package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.DepartmentRepo;
import com.learning.spring.rest.employees.dao.EmployeeRepo;
import com.learning.spring.rest.employees.dto.*;
import com.learning.spring.rest.employees.exceptions.department.DepartmentNotFoundByNameException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.mappers.EmployeeMapper;
import com.learning.spring.rest.employees.model.Department;
import com.learning.spring.rest.employees.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

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

    @Override
    public BaseEmployeeDTO getEmployeeById(int id) throws EmployeeNotFoundException {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id=" + id, id));
        employee.setDeptName(employee);
        BaseEmployeeDTO baseEmployeeDTO = employeeMapper.convertFromEmpToEmpDto(employee);
        logger.info("Information for employee with id=" + id + ": Name={}, Salary={}", employee.getName(), employee.getSalary());
        return baseEmployeeDTO;
    }


    @Transactional
    @Override
    public BaseEmployeeDTO save(EmployeePOSTReq_DTO emp) {

        Employee employee = employeeMapper.convertFromEmpPostDTOToEmp(emp);
        employee.setStartDate(LocalDate.now());
        employee.setSalary(emp.getSalary());
        Employee savedEmp = employeeRepo.save(employee);
        BaseEmployeeDTO baseEmployeeDTO = employeeMapper.convertFromEmpToEmpDto(savedEmp);
        logger.info("Employee with id {} and name {} was added successfully!", emp.getId(), emp.getName());

        return baseEmployeeDTO;

    }

    @Override
    public EmployeePUTResponse_DTO updateEmployee(int id, EmployeePUTReq_DTO emp) throws EmployeeNotFoundException {

        Employee employeeToBeUpdated = employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id=" + id, id));
        employeeToBeUpdated.setName(emp.getName());
        employeeToBeUpdated.setSalary(emp.getSalary());
        employeeToBeUpdated.setBonus(emp.isBonus());
        employeeRepo.save(employeeToBeUpdated);
        EmployeePUTResponse_DTO baseEmployeeDTO = employeeMapper.convertFromEmpToEmpPutResponseDto(employeeToBeUpdated);
        logger.info("Details of employee with id:{} were successfully updated!", id);
        return baseEmployeeDTO;
    }

    @Override
    public EmployeeWithDeptNameDTO assignDepartment(int employeeId, String deptName) throws EmployeeNotFoundException, DepartmentNotFoundByNameException {
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id=" + employeeId, employeeId));
        Department department = departmentRepo.findByDeptName(deptName);
        if (department == null) {
            throw new DepartmentNotFoundByNameException("Department not found with name=" + deptName, deptName);
        } else {
            employee.setDepartment(department);
        }
        Employee savedEmployee = employeeRepo.save(employee);
        EmployeeWithDeptNameDTO emp = employeeMapper.convertFromEmpToEmpDto(savedEmployee);
        return emp;

    }
}
