package com.learning.spring.rest.employees.mappers;

import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.dto.EmployeePUTResponse_DTO;
import com.learning.spring.rest.employees.dto.ManagerDTO;
import com.learning.spring.rest.employees.dto.UserDTO;
import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.model.Manager;
import com.learning.spring.rest.employees.model.User;
import com.learning.spring.rest.employees.services.CommunityServiceImpl;
import com.learning.spring.rest.employees.services.ManagerServiceImpl;
import com.learning.spring.rest.employees.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static com.learning.spring.rest.employees.utils.DateAndTimeUtils.getCurrentDate;

@Component
public class UserMapper {


    private CommunityServiceImpl CommunityService;
    private ManagerServiceImpl managerService;

    @Autowired
    public UserMapper(CommunityServiceImpl CommunityService, ManagerServiceImpl managerService) {
        this.CommunityService = CommunityService;
        this.managerService = managerService;
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
        return user;
    }

    public EmployeeDTO convertFromEmpToEmpDto(User user) {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName(user.getFirstName());
        employeeDTO.setLastName(user.getLastName());
        employeeDTO.setSex(user.getSex());
        employeeDTO.setEmail(user.getEmail());
        employeeDTO.setPhoneNumber(user.getPhoneNumber());

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
        return new UserDTO();

    }

    public EmployeeDTO convertFromEmpTOEmployeeDTO(Employee employee1) {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setUserId(employee1.getUserId());
        employeeDTO.setFirstName(employee1.getFirstName());
        employeeDTO.setLastName(employee1.getLastName());
        employeeDTO.setSex(employee1.getSex());
        employeeDTO.setEmail(employee1.getEmail());
        employeeDTO.setPhoneNumber(employee1.getPhoneNumber());
        employeeDTO.setCommunityName(employee1.getCommunity().getCommunityName());
        employeeDTO.setStartDate(getCurrentDate());
        employeeDTO.setBonus(employee1.getBonus());
        employeeDTO.setSalary(employee1.getSalary());
        return employeeDTO;

    }

    public Employee convertFromEmpDtoTOEmployee(EmployeeDTO dto) {

        Employee employee = new Employee();
        employee.setUserId(dto.getUserId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setSex(dto.getSex());
        employee.setEmail(dto.getEmail());
        employee.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setCommunity(CommunityService.getDefaultCommunity(Constants.DEFAULT_Community));
        employee.setStartDate(getCurrentDate());
        employee.setBonus(dto.getBonus());
        employee.setSalary(dto.getSalary());
        return employee;

    }


    public Manager convertFromManagerDtoTOManager(ManagerDTO dto) {

        Manager manager = new Manager();
        manager.setUserId(dto.getUserId());
        manager.setFirstName(dto.getFirstName());
        manager.setLastName(dto.getLastName());
        manager.setSex(dto.getSex());
        manager.setEmail(dto.getEmail());
        manager.setPassword(dto.getPassword());
        manager.setPhoneNumber(dto.getPhoneNumber());
        return manager;

    }

    public ManagerDTO convertFromManagerTOManagerDto(Manager manager) {

        ManagerDTO managerDTO = new ManagerDTO();
        managerDTO.setUserId(manager.getUserId());
        managerDTO.setFirstName(manager.getFirstName());
        managerDTO.setLastName(manager.getLastName());
        managerDTO.setSex(manager.getSex());
        managerDTO.setEmail(manager.getEmail());
        managerDTO.setPhoneNumber(manager.getPhoneNumber());
        return managerDTO;

    }


    public EmployeePUTResponse_DTO convertFromEmpToEmpPutResponseDto(Employee emp) {

//        EmployeePUTResponse_DTO dto = new EmployeePUTResponse_DTO();
//        dto.setId(emp.getId());
//        dto.setName(emp.getName());
//        dto.setSex(emp.getSex());
//        dto.setCommunityName(emp.getCommunity().getCommunityName());
//        return dto;
        return new EmployeePUTResponse_DTO();

    }

}
