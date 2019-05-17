package com.learning.spring.rest.employees.mappers;

import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee convertFromEmpDtoToEmp(EmployeeDTO dto) {

        Employee e = new Employee();
        e.setDepartment(dto.getDepartment());
        e.setName(dto.getName());
        return e;
    }

    public EmployeeDTO convertFromEmpToEmpDto(Employee emp) {

        EmployeeDTO dto = new EmployeeDTO();
        dto.setName(emp.getName());
        dto.setDepartment(emp.getDepartment());
        dto.setSex(emp.getSex());

        return dto;

    }
}
