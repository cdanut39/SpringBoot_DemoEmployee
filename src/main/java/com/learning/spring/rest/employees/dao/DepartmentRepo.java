package com.learning.spring.rest.employees.dao;

import com.learning.spring.rest.employees.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
    Department findByDeptName(String name);
}
