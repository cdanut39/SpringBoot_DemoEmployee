package com.learning.spring.rest.employees.mappers;

import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.dto.ManagerDTO;
import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.model.Manager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserMapper {

    public UserMapper() {
    }

    public EmployeeDTO convertFromEmpTOEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setUserId(employee.getUserId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setSex(employee.getSex());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPhoneNumber(employee.getPhoneNumber());
        employeeDTO.setCommunityName(employee.getCommunity().getCommunityName());
        employeeDTO.setStartDate(employee.getStartDate());

        return employeeDTO;
    }


    public Employee convertFromEmpDtoTOEmployee(EmployeeDTO dto) {
        Employee employee = new Employee();

        employee.setUserId(dto.getUserId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setSex(dto.getSex());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setStartDate(dto.getStartDate());
        employee.setCommunityName(dto.getCommunityName());


        return employee;
    }


    public Manager convertFromManagerDtoToManagerSave(ManagerDTO dto) {
        Manager manager = new Manager();

        manager.setUserId(dto.getUserId());
        manager.setFirstName(dto.getFirstName());
        manager.setLastName(dto.getLastName());
        manager.setSex(dto.getSex());
        manager.setEmail(dto.getEmail());
        manager.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        manager.setPhoneNumber(dto.getPhoneNumber());

        return manager;
    }

    public Manager convertFromManagerDtoToManager(ManagerDTO dto) {
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



    public ManagerDTO convertFromManagerToManagerDto(Manager manager) {
        ManagerDTO managerDTO = new ManagerDTO();

        managerDTO.setUserId(manager.getUserId());
        managerDTO.setFirstName(manager.getFirstName());
        managerDTO.setLastName(manager.getLastName());
        managerDTO.setSex(manager.getSex());
        managerDTO.setEmail(manager.getEmail());
        managerDTO.setPhoneNumber(manager.getPhoneNumber());

        return managerDTO;
    }

}
