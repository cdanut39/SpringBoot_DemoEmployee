package com.learning.spring.rest.employees.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@DiscriminatorValue("Manager")
public class Manager extends User {
    @OneToMany(mappedBy = "manager", cascade = CascadeType.PERSIST, orphanRemoval = false)
    private Set<Project> projects;


}
