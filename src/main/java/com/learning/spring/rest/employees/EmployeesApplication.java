package com.learning.spring.rest.employees;

import com.learning.spring.rest.employees.dto.DepartmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication()
public class EmployeesApplication {
//
//    @Autowired
//    DepartmentDTO departmentDTO;

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));   // It will set UTC timezone
        System.out.println("Spring boot application running in UTC timezone :" + LocalDate.now());   // It will print UTC timezone
    }

    public static void main(String[] args) {
        SpringApplication.run(EmployeesApplication.class, args);
    }

}
