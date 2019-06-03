package com.learning.spring.rest.employees.dao;

import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {

    //    @Query(value = "Select * from employees order by salary", nativeQuery = true)
    @Query(value = "SELECT e FROM User e ORDER BY salary DESC")
    List<Employee> getEmployeesOrderBySalary();
}
