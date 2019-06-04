package com.learning.spring.rest.employees.model;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("Manager")
public class Manager extends User {
    @OneToMany(mappedBy = "manager", cascade = CascadeType.PERSIST, orphanRemoval = false)
    private Set<Employee> employees;

    public Manager() {
        employees=new HashSet<>();
    }

    public Manager(Set<Employee> employees) {
        this.employees = employees;
    }



    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
