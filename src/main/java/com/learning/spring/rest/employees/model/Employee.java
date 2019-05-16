package com.learning.spring.rest.employees.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int salary;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Department department;

//    private Gender sex;
//
//    private LocalDate startDate;


    public Employee() {
    }

    public Employee(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

//    public Gender getSex() {
//        return sex;
//    }
//
//    public void setSex(Gender sex) {
//        this.sex = sex;
//    }
//
//    public LocalDate getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(LocalDate startDate) {
//        this.startDate = startDate;
//    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
//                ", department=" + department +
                '}';
    }

    public enum Gender {
        M, F;
    }
}
