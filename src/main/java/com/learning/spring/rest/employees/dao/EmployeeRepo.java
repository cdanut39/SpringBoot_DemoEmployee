package com.learning.spring.rest.employees.dao;

import com.learning.spring.rest.employees.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    //    @Query(value = "Select * from employees order by salary", nativeQuery = true)
    @Query(value = "SELECT e FROM Employee e ORDER BY salary DESC")
    List<Employee> getEmployeesOrderBySalary();
}
