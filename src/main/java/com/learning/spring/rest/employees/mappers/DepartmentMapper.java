package com.learning.spring.rest.employees.mappers;

import com.learning.spring.rest.employees.dto.BaseDepartmentDTO;
import com.learning.spring.rest.employees.dto.DepartmentDTO;
import com.learning.spring.rest.employees.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@PropertySource(value = "classpath:config.properties")
public class DepartmentMapper {

    @Value("${companyName}")
    private String companyName;

    private EmployeeMapper empMapper;

    @Autowired
    public DepartmentMapper(EmployeeMapper empMapper) {
        this.empMapper = empMapper;
    }

    public DepartmentDTO convertFromDeptToDeptDtoForGet(Department dept) {

        DepartmentDTO dto = new DepartmentDTO();
        dto.setDeptName(dept.getDeptName());
        dto.setDeptId(dept.getDeptId());
        dto.setCompanyName(companyName);
        dto.setEmployees(dept.getEmployees().stream()
                .map(empMapper::convertFromEmpToEmpDtoNODeptName)
                .collect(Collectors.toList()));
        return dto;
    }

    public BaseDepartmentDTO convertFromDeptToBaseDeptDto(Department dept) {

        BaseDepartmentDTO dto = new BaseDepartmentDTO();
        dto.setDeptName(dept.getDeptName());
        dto.setDeptId(dept.getDeptId());
        return dto;
    }

    public Department convertFromBaseDeptDtoToDept(BaseDepartmentDTO baseDepartmentDTO) {

        Department department = new Department();
        department.setDeptName(baseDepartmentDTO.getDeptName());

        return department;
    }

}
