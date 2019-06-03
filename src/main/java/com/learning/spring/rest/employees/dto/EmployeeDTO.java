package com.learning.spring.rest.employees.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learning.spring.rest.employees.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class EmployeeDTO extends UserDTO {

    private int salary;
    private Boolean bonus;
    @JsonProperty(value = "firstDay")
    private LocalDate startDate;

    public EmployeeDTO() {
    }

    public EmployeeDTO(int salary, Boolean bonus, LocalDate startDate) {
        this.salary = salary;
        this.bonus = bonus;
        this.startDate = startDate;
    }

    public EmployeeDTO(int userId, @NotBlank(message = "Name cannot be blank") @Size(min = 3, max = 32, message = "First name has to be equal to or greater than 3 and less than 20 characters") String firstName, @NotBlank(message = "Name cannot be blank") @Size(min = 3, max = 32, message = "Last name has to be equal to or greater than 3 and less than 20 characters") String lastName, User.Gender sex, long phoneNumber, @NotBlank(message = "Username cannot be blank") @Size(min = 3, max = 10, message = "Username has to be equal to or greater than 3 and less than 10 characters") String username, String password, @Email String email, User.UserType userType, int salary, Boolean bonus, LocalDate startDate) {
        super(userId, firstName, lastName, sex, phoneNumber, username, password, email, userType);
        this.salary = salary;
        this.bonus = bonus;
        this.startDate = startDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Boolean getBonus() {
        return bonus;
    }

    public void setBonus(Boolean bonus) {
        this.bonus = bonus;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
