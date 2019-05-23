package com.learning.spring.rest.employees.mappers;

import com.learning.spring.rest.employees.dto.BaseEmployeeDTO;
import com.learning.spring.rest.employees.dto.EmployeeWithDeptNameDTO;
import com.learning.spring.rest.employees.model.Employee;
import org.springframework.stereotype.Component;

import static com.learning.spring.rest.employees.utils.DateAndTimeUtils.getCurrentDate;

@Component
public class EmployeeMapper {

    public Employee convertFromEmpDtoToEmp(BaseEmployeeDTO dto) {

        Employee e = new Employee();
        e.setName(dto.getName());
        e.setStartDate(getCurrentDate());
        return e;
    }

    public EmployeeWithDeptNameDTO convertFromEmpToEmpDto(Employee emp) {

        EmployeeWithDeptNameDTO dto = new EmployeeWithDeptNameDTO();
        dto.setName(emp.getName());
        dto.setId(emp.getId());
        dto.setDeptName(emp.getDepartment().getDeptName());
        dto.setStartDate(emp.getStartDate());
        dto.setSex(emp.getSex());

        return dto;

    }

    public BaseEmployeeDTO convertFromEmpToEmpDtoNODeptName(Employee emp) {

        BaseEmployeeDTO dto = new BaseEmployeeDTO();
        dto.setName(emp.getName());
        dto.setId(emp.getId());
        dto.setStartDate(emp.getStartDate());
        dto.setSex(emp.getSex());

        return dto;

    }
}
