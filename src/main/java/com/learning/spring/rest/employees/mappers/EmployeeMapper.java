package com.learning.spring.rest.employees.mappers;

import com.learning.spring.rest.employees.dto.BaseEmployeeDTO;
import com.learning.spring.rest.employees.dto.EmployeePOSTReq_DTO;
import com.learning.spring.rest.employees.dto.EmployeePUTResponse_DTO;
import com.learning.spring.rest.employees.dto.EmployeeWithDeptNameDTO;
import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.services.DepartmentServiceImpl;
import com.learning.spring.rest.employees.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.learning.spring.rest.employees.utils.DateAndTimeUtils.getCurrentDate;

@Component
public class EmployeeMapper {


    private DepartmentServiceImpl departmentService;

    @Autowired
    public EmployeeMapper(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    public Employee convertFromEmpDtoToEmp(BaseEmployeeDTO dto) {
        Employee e = new Employee();
        e.setName(dto.getName());
        e.setStartDate(getCurrentDate());
        return e;
    }

    public EmployeeWithDeptNameDTO convertFromEmpToEmpDto(Employee emp) {

        EmployeeWithDeptNameDTO dto = new EmployeeWithDeptNameDTO();
        dto.setId(emp.getId());
        dto.setName(emp.getName());
        dto.setSex(emp.getSex());
        dto.setDeptName(emp.getDepartment().getDeptName());
        dto.setStartDate(emp.getStartDate());

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

    public Employee convertFromEmpPostDTOToEmp(EmployeePOSTReq_DTO emp) {

        Employee employee = new Employee();
        employee.setName(emp.getName());
        employee.setId(emp.getId());
        employee.setDepartment(departmentService.getDefaultDepartment(Constants.DEFAULT_DEPARTMENT));
        employee.setStartDate(emp.getStartDate());
        employee.setSex(emp.getSex());
        employee.setBonus(emp.isBonus());
        return employee;

    }

    public EmployeePUTResponse_DTO convertFromEmpToEmpPutResponseDto(Employee emp) {

        EmployeePUTResponse_DTO dto = new EmployeePUTResponse_DTO();
        dto.setId(emp.getId());
        dto.setName(emp.getName());
        dto.setSex(emp.getSex());
        dto.setDeptName(emp.getDepartment().getDeptName());
        return dto;

    }
}
