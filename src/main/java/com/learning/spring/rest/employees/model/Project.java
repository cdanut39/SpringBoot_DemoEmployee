package com.learning.spring.rest.employees.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;
    private String projectName;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Manager manager;
    @OneToMany(mappedBy = "project", cascade = CascadeType.MERGE)
    private Set<Employee> employees;

}
