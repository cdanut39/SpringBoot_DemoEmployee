package com.learning.spring.rest.employees.mappers;

import com.learning.spring.rest.employees.dto.DepartmentDTO;
import com.learning.spring.rest.employees.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DepartmentMapper {

    @Autowired
    private EmployeeMapper empMapper;

    @Autowired
    private DepartmentDTO dto;

    public DepartmentDTO convertFromDeptToDeptDto(Department dept) {

//        DepartmentDTO dto = new DepartmentDTO();
        dto.setDeptName(dept.getDeptName());
        dto.setDeptId(dept.getDeptId());

        dto.setEmployees(dept.getEmployees().stream()
                .map(empMapper::convertFromEmpToEmpDtoNODeptName)
                .collect(Collectors.toList()));


        return dto;
    }
}
