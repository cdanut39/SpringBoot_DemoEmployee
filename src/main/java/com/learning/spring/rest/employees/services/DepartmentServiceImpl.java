package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.DepartmentRepo;
import com.learning.spring.rest.employees.dto.BaseDepartmentDTO;
import com.learning.spring.rest.employees.dto.DepartmentDTO;
import com.learning.spring.rest.employees.exceptions.department.DefaultDepartmentCanNotBeRemovedException;
import com.learning.spring.rest.employees.exceptions.department.DepartmentAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.department.DepartmentNotFoundByIdException;
import com.learning.spring.rest.employees.mappers.DepartmentMapper;
import com.learning.spring.rest.employees.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger logger = LogManager.getLogger(DepartmentServiceImpl.class);

    private DepartmentRepo departmentRepo;
    private DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepo departmentRepo, @Lazy DepartmentMapper departmentMapper) {
        this.departmentRepo = departmentRepo;
        this.departmentMapper = departmentMapper;
    }

    @Override
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

    @Override
    public void deleteDepartmentById(int id) throws DepartmentNotFoundByIdException, DefaultDepartmentCanNotBeRemovedException {
        if (id == 1) {
            throw new DefaultDepartmentCanNotBeRemovedException("Default department can not be removed");
        }
        Department department = departmentRepo.findById(id).orElseThrow(() -> new DepartmentNotFoundByIdException("Department not found with id=" + id, id));
        departmentRepo.delete(department);
    }

    @Override
    public DepartmentDTO getDepartmentById(int id) throws DepartmentNotFoundByIdException {
        Department department = departmentRepo.findById(id).orElseThrow(() -> new DepartmentNotFoundByIdException("Department not found with id=" + id, id));
        DepartmentDTO departmentDTO = departmentMapper.convertFromDeptToDeptDtoForGet(department);
        logger.info("Information for department with id=" + id + ": Name={}", department.getDeptName());
        return departmentDTO;
    }

    @Override
    public Department getDefaultDepartment(int id) {
        return departmentRepo.getOne(id);

    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepo.findAll();
        List<DepartmentDTO> depts = departments.stream().map(departmentMapper::convertFromDeptToDeptDtoForGet).collect(Collectors.toList());
        return depts;
    }
}
