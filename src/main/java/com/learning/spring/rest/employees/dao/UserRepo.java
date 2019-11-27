package com.learning.spring.rest.employees.dao;

import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.model.Manager;
import com.learning.spring.rest.employees.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPasswordToken(String token);

    @Query(value = "Select * from users where user_type='Employee' and user_id=?", nativeQuery = true)
    Optional<Employee> findEmployeeById(int id);

    @Query(value = "Select * from users where user_type='Manager' and user_id=?", nativeQuery = true)
    Optional<Manager> findManagerById(int id);

    @Query(value = "Select * from users where user_type='Employee'", nativeQuery = true)
    List<Employee> findAllEmployees();

    @Query(value = "Select * from users where user_type='Employee'", nativeQuery = true)
    Page<Employee> findAllEmployees(Pageable pageable);

    @Query(value = "Select * from users where user_type='Manager' and first_name=? and last_name=?", nativeQuery = true)
    Optional<Manager> findManagerByName(String firstName, String lastName);
}
