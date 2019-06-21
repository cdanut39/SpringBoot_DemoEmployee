package com.learning.spring.rest.employees.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learning.spring.rest.employees.model.Role;
import com.learning.spring.rest.employees.model.User;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

public class EmployeeDTO extends UserDTO {

    @Min(value = 2000, message = "Minimum salary is 2000 EUR")
    @ApiModelProperty(required = true)
    private int salary;
    @ApiModelProperty(required = true)
    private Boolean bonus;
    @ApiModelProperty(readOnly = true, notes = "employee first day in company")
    @JsonProperty(value = "firstDay")
    private LocalDate startDate;

    private String communityName;

    public EmployeeDTO() {
    }

    public EmployeeDTO(@Min(value = 2000, message = "Minimum salary is 2000 EUR") int salary, Boolean bonus, LocalDate startDate, String communityName) {
        this.salary = salary;
        this.bonus = bonus;
        this.startDate = startDate;
        this.communityName = communityName;
    }

    public EmployeeDTO(int userId, @NotBlank(message = "Name cannot be blank") @Size(min = 3, max = 32,
            message = "First name has to be equal to or greater than 3 and less than 20 characters") String firstName, @NotBlank(message = "Name cannot be blank") @Size(min = 3, max = 32, message = "Last name has to be equal to or greater than 3 and less than 20 characters") String lastName, User.Gender sex, long phoneNumber, String password, @Email String email, Set<Role> roles, @Min(value = 2000, message = "Minimum salary is 2000 EUR") int salary, Boolean bonus, LocalDate startDate, String communityName) {
        super(userId, firstName, lastName, sex, phoneNumber, password, email, roles);
        this.salary = salary;
        this.bonus = bonus;
        this.startDate = startDate;
        this.communityName = communityName;
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

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
