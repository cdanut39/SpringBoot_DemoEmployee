package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.DepartmentRepo;
import com.learning.spring.rest.employees.dao.UserRepo;
import com.learning.spring.rest.employees.dto.BaseDepartmentDTO;
import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.exceptions.department.DepartmentNotFoundByNameException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.user.UserAlreadyExistsException;
import com.learning.spring.rest.employees.mappers.UserMapper;
import com.learning.spring.rest.employees.model.Department;
import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.learning.spring.rest.employees.utils.Constants.USER_EXISTS;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

    private UserRepo userRepo;
    private DepartmentRepo departmentRepo;
    private UserMapper userMapper;
    private DepartmentServiceImpl departmentService;

    @Autowired
    public EmployeeServiceImpl(UserRepo UserRepo, DepartmentRepo departmentRepo, UserMapper userMapper, DepartmentServiceImpl departmentService) {
        this.userRepo = UserRepo;
        this.departmentRepo = departmentRepo;
        this.userMapper = userMapper;
        this.departmentService = departmentService;
    }

    @Override
    public EmployeeDTO getEmployeeById(int id) throws EmployeeNotFoundException {
        Employee employee = userRepo.findEmployeeById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found with id=" + id, id);
        }
        employee.setDeptName(employee);
        EmployeeDTO userDTO = userMapper.convertFromEmpTOEmployeeDTO(employee);
//        logger.info("Information for employee with id=" + id + ": Name={}, Salary={}", employee.getName(), employee.getSalary());
        return userDTO;
    }


    @Transactional
    @Override
    public EmployeeDTO save(EmployeeDTO employeeDto) throws UserAlreadyExistsException {
        Employee employeeToBeSaved = null;
        String email = employeeDto.getEmail();
        User user = userRepo.findByEmail(email);
        if (user != null) {
            throw new UserAlreadyExistsException(USER_EXISTS, email);
        } else {
            employeeToBeSaved = userMapper.convertFromEmpDtoTOEmployee(employeeDto);
        }
        Employee savedEmployee = userRepo.save(employeeToBeSaved);
        EmployeeDTO empDto = userMapper.convertFromEmpToEmpDto(savedEmployee);
        return empDto;
    }

    //    @Override
//    public EmployeePUTResponse_DTO updateEmployee(int id, EmployeePUTReq_DTO emp) throws EmployeeNotFoundException {
//
//        Employee employeeToBeUpdated = userRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id=" + id, id));
////        employeeToBeUpdated.setName(emp.getName());
//        employeeToBeUpdated.setSalary(emp.getSalary());
//        employeeToBeUpdated.setBonus(emp.isBonus());
//        userRepo.save(employeeToBeUpdated);
//        EmployeePUTResponse_DTO baseEmployeeDTO = userMapper.convertFromEmpToEmpPutResponseDto(employeeToBeUpdated);
//        logger.info("Details of employee with id:{} were successfully updated!", id);
//        return baseEmployeeDTO;
//    }
//
    @Override
    public EmployeeDTO assignDepartment(int employeeId, BaseDepartmentDTO dept) throws EmployeeNotFoundException, DepartmentNotFoundByNameException {
        Employee employee = userRepo.findEmployeeById(employeeId);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found with id=" + employeeId, employeeId);
        }
        Department department = departmentRepo.findByDeptName(dept.getDeptName());
        if (department == null) {
            throw new DepartmentNotFoundByNameException("Department not found with name=" + dept.getDeptName(), dept.getDeptName());
        } else {
            employee.setDepartment(department);
        }
        Employee savedEmployee = userRepo.save(employee);
        EmployeeDTO emp = userMapper.convertFromEmpTOEmployeeDTO(savedEmployee);
        return emp;

    }

    @Override
    public void removeEmployee(int id) throws EmployeeNotFoundException {
        Employee employee = userRepo.findEmployeeById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Couldn't delete. Employee with id=" + id + " doesn't exist", id);
        } else {
            userRepo.delete(employee);
            logger.info("Successfully removed employee with id={},{}", employee.getUserId(), employee.getFirstName());
        }
    }
}
