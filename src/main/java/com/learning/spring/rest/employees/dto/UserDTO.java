package com.learning.spring.rest.employees.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.learning.spring.rest.employees.model.Role;
import com.learning.spring.rest.employees.model.User;
import com.learning.spring.rest.employees.utils.validators.RoPhoneNumberConstraint;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserDTO {

    @ApiModelProperty(readOnly = true)
    private int userId;

    @ApiModelProperty(required = true)
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 32, message = "First name has to be equal to or greater than 3 and less than 20 characters")
    private String firstName;

    @ApiModelProperty(required = true)
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 32, message = "Last name has to be equal to or greater than 3 and less than 20 characters")
    private String lastName;

    @ApiModelProperty(required = true)
    @Enumerated(EnumType.STRING)
    @NotNull
    private User.Gender sex;

    @ApiModelProperty(required = true)
    @RoPhoneNumberConstraint
    private String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ApiModelProperty(required = true)
    @Email
    @NotBlank
    private String email;

    @JsonIgnore
    private Set<Role> roles;

    public UserDTO() {
    }


    public UserDTO(UserDTOBuilder<?> builder) {
        userId = builder.userId;
        firstName = builder.firstName;
        lastName = builder.lastName;
        sex = builder.sex;
        email = builder.email;
        password = builder.password;
        phoneNumber = builder.phoneNumber;
        roles = builder.roles;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User.Gender getSex() {
        return sex;
    }

    public void setSex(User.Gender sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public abstract static class UserDTOBuilder<T extends UserDTOBuilder<T>> {
        private int userId;
        private String firstName;
        private String lastName;
        private User.Gender sex;
        private String phoneNumber;
        private String password;
        private String email;
        private Set<Role> roles;

        public abstract T getThis();

        public UserDTOBuilder() {
        }

        public T setUserId(int userId) {
            this.userId = userId;
            return getThis();
        }

        public T setFirstName(String firstName) {
            this.firstName = firstName;
            return getThis();
        }

        public T setLastName(String lastName) {
            this.lastName = lastName;
            return getThis();
        }

        public T setSex(User.Gender sex) {
            this.sex = sex;
            return getThis();
        }

        public T setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return getThis();
        }

        public T setPassword(String password) {
            this.password = password;
            return getThis();
        }

        public T setEmail(String email) {
            this.email = email;
            return getThis();
        }

        public T setRoles(Set<Role> roles) {
            this.roles = roles;
            return getThis();
        }

        public UserDTO build() {
            return new UserDTO(this);
        }
    }
}
