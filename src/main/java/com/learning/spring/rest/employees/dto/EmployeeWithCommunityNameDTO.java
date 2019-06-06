package com.learning.spring.rest.employees.dto;

public class EmployeeWithCommunityNameDTO extends UserDTO {

    private String communityName;

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
