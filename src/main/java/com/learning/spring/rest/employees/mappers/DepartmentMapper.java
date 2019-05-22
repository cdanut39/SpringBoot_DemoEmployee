package com.learning.spring.rest.employees.mappers;

import com.learning.spring.rest.employees.dto.DepartmentDTO;
import com.learning.spring.rest.employees.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentDTO convertFromDeptToDeptDto(Department dept) {

        DepartmentDTO dto = new DepartmentDTO();
        dto.setDeptName(dept.getDeptName());
        dto.setDeptId(dept.getDeptId());
        dto.setEmployees(dept.getEmployees());

        return dto;

    }
}
