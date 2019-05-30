package com.learning.spring.rest.employees;

import com.learning.spring.rest.employees.dto.DepartmentDTO;
import com.learning.spring.rest.employees.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication()
public class EmployeesApplication {


    public static void main(String[] args) {
        SpringApplication.run(EmployeesApplication.class, args);
    }



}
