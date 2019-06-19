package com.learning.spring.rest.employees.dao;

import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Query(value = "Select * from users where user_type='Employee' and user_id=?", nativeQuery = true)
    Employee findEmployeeById(int id);

    @Query(value = "Select * from users where user_type='Employee'", nativeQuery = true)
    List<Employee> findAllEmployees();


}
