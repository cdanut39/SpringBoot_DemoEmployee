package com.learning.spring.rest.employees.dao;

import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.model.Manager;
import com.learning.spring.rest.employees.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByEmail(String email);
    @Query(value = "Select * from users where user_type='Employee' and user_id=?",nativeQuery = true)
    Employee findEmployeeById(int id);

}
