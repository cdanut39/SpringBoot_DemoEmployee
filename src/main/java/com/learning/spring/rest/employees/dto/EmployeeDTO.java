package com.learning.spring.rest.employees.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

public class EmployeeDTO extends UserDTO {


    @ApiModelProperty(readOnly = true, notes = "employee first day in company")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    private String communityName;

    public EmployeeDTO() {
    }

    public EmployeeDTO(EmployeeDTOBuilder builder) {
        super(builder);
        startDate = builder.startDate;
        communityName = builder.communityName;
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

    public static class EmployeeDTOBuilder extends UserDTO.UserDTOBuilder<EmployeeDTOBuilder> {

        private LocalDate startDate;
        private String communityName;

        public EmployeeDTOBuilder() {
        }

        @Override
        public EmployeeDTOBuilder getThis() {
            return this;
        }

        public EmployeeDTOBuilder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public EmployeeDTOBuilder setCommunityName(String communityName) {
            this.communityName = communityName;
            return this;
        }

        public EmployeeDTO build() {
            return new EmployeeDTO(this);
        }
    }
}
