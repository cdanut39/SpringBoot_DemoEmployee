package com.learning.spring.rest.employees.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "userType")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private User.Gender sex;
    private long phoneNumber;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name="role_ID")
    private Role role;

    public enum Gender {
        M, F;
    }

    public enum UserType {
        EMPLOYEE, MANAGER, ADMIN
    }

}


