package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.DepartmentRepo;
import com.learning.spring.rest.employees.dto.BaseDepartmentDTO;
import com.learning.spring.rest.employees.dto.DepartmentDTO;
import com.learning.spring.rest.employees.exceptions.department.DepartmentAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.department.DepartmentNotFoundException;
import com.learning.spring.rest.employees.mappers.DepartmentMapper;
import com.learning.spring.rest.employees.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger logger = LogManager.getLogger(DepartmentServiceImpl.class);

    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    DepartmentMapper departmentMapper;

    public BaseDepartmentDTO addDepartment(Department department) throws DepartmentAlreadyExistsException {
        Department savedDepartment = null;
        Department dept = departmentRepo.findByDeptName(department.getDeptName());
        if (dept == null) {
            savedDepartment = departmentRepo.save(department);
        } else {
            throw new DepartmentAlreadyExistsException("Department already exists!");
        }
        BaseDepartmentDTO departmentDTO = departmentMapper.convertFromDeptToBaseDeptDto(savedDepartment);
        return departmentDTO;
    }

    public void deleteDepartmentById(int id) throws DepartmentNotFoundException {
        Department department = departmentRepo.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department not found with id=" + id, id));
        departmentRepo.delete(department);
    }

    public DepartmentDTO getDepartmentById(int id) throws DepartmentNotFoundException {
        Department department = departmentRepo.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department not found with id=" + id, id));
        DepartmentDTO departmentDTO = departmentMapper.convertFromDeptToDeptDtoForGet(department);
        logger.info("Information for department with id=" + id + ": Name={}", department.getDeptName());
        return departmentDTO;
    }
}
