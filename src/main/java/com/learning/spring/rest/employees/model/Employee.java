package com.learning.spring.rest.employees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.time.LocalDate;

@JsonPropertyOrder({"id", "name", "sex", "deptName", "salary", "bonus", "firstDay"})
//pentru ordinea afisarii JSON-ului la GET request
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int salary;
    @Enumerated(EnumType.STRING)
    private Gender sex;

    @JsonProperty(value = "firstDay")
    private LocalDate startDate;


    public Boolean getBonus() {
        return bonus;
    }

    public void setBonus(Boolean bonus) {
        this.bonus = bonus;
    }

    private Boolean bonus;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Department department;

    @Transient
    private String deptName;

    public Employee() {
    }

    public Employee(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

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


    public void setDeptName(Employee employee) {
        this.deptName = employee.getDepartment().getDeptName();
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptName() {
        return deptName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }

    public enum Gender {
        M, F;
    }

}
