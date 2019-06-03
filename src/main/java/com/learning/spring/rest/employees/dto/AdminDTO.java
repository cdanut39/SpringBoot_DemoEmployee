package com.learning.spring.rest.employees.dto;

import com.learning.spring.rest.employees.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AdminDTO extends UserDTO {
    private String office;

    public AdminDTO() {
    }

    public AdminDTO(String office) {
        this.office = office;
    }

    public AdminDTO(int userId, @NotBlank(message = "Name cannot be blank") @Size(min = 3, max = 32, message = "First name has to be equal to or greater than 3 and less than 20 characters") String firstName, @NotBlank(message = "Name cannot be blank") @Size(min = 3, max = 32, message = "Last name has to be equal to or greater than 3 and less than 20 characters") String lastName, User.Gender sex, long phoneNumber, @NotBlank(message = "Username cannot be blank") @Size(min = 3, max = 10, message = "Username has to be equal to or greater than 3 and less than 10 characters") String username, String password, @Email String email, User.UserType userType, String office) {
        super(userId, firstName, lastName, sex, phoneNumber, username, password, email, userType);
        this.office = office;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}
