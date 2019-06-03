package com.learning.spring.rest.employees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends User {

    private String office;

    public Admin() {
    }

    public Admin(String office) {
        this.office = office;
    }

    public Admin(int userId, String firstName, String lastName, Gender sex, long phoneNumber, String username, String password, String email, String office) {
        super(userId, firstName, lastName, sex, phoneNumber, username, password, email);
        this.office = office;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}
