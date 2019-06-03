package com.learning.spring.rest.employees.mappers;

import com.learning.spring.rest.employees.dto.AdminDTO;
import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.dto.EmployeePUTResponse_DTO;
import com.learning.spring.rest.employees.dto.UserDTO;
import com.learning.spring.rest.employees.model.Admin;
import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.model.User;
import com.learning.spring.rest.employees.services.DepartmentServiceImpl;
import com.learning.spring.rest.employees.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.learning.spring.rest.employees.utils.DateAndTimeUtils.getCurrentDate;

@Component
public class UserMapper {


    private DepartmentServiceImpl departmentService;

    @Autowired
    public UserMapper(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    public User convertFromUserDtoToUser(UserDTO dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setSex(dto.getSex());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setUsername(dto.getUsername());
        return user;
    }

    public EmployeeDTO convertFromEmpToEmpDto(User user) {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName(user.getFirstName());
        employeeDTO.setLastName(user.getLastName());
        employeeDTO.setSex(user.getSex());
        employeeDTO.setEmail(user.getEmail());
        employeeDTO.setPassword(user.getPassword());
        employeeDTO.setPhoneNumber(user.getPhoneNumber());
        employeeDTO.setUsername(user.getUsername());

        return employeeDTO;

    }

    public UserDTO convertFromUserToUserDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setSex(user.getSex());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setUsername(user.getUsername());
        return new UserDTO();

    }

    public <T extends User> convertFromEmpDtoTOEmployee(EmployeeDTO dto) {

        Employee employee = new Employee();
        employee.setUserId(dto.getUserId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setSex(dto.getSex());
        employee.setEmail(dto.getEmail());
        employee.setPassword(dto.getPassword());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setUsername(dto.getUsername());
        employee.setDepartment(departmentService.getDefaultDepartment(Constants.DEFAULT_DEPARTMENT));
        employee.setStartDate(getCurrentDate());
        employee.setBonus(dto.getBonus());
        employee.setSalary(dto.getSalary());
        return employee;

    }

    public Admin convertFromAdminDtoTOAdmin(AdminDTO dto) {

        Admin admin = new Admin();
        admin.setUserId(dto.getUserId());
        admin.setFirstName(dto.getFirstName());
        admin.setLastName(dto.getLastName());
        admin.setSex(dto.getSex());
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        admin.setPhoneNumber(dto.getPhoneNumber());
        admin.setUsername(dto.getUsername());
        admin.setOffice(dto.getOffice());
        return admin;

    }

    public EmployeePUTResponse_DTO convertFromEmpToEmpPutResponseDto(Employee emp) {

//        EmployeePUTResponse_DTO dto = new EmployeePUTResponse_DTO();
//        dto.setId(emp.getId());
//        dto.setName(emp.getName());
//        dto.setSex(emp.getSex());
//        dto.setDeptName(emp.getDepartment().getDeptName());
//        return dto;
        return new EmployeePUTResponse_DTO();

    }

}
