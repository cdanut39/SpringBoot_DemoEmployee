package com.learning.spring.rest.employees.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonPropertyOrder(value = {"communityId","communityName"})
public class BaseCommunityDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int communityId;
    @Size(min = 2, max = 16, message = "Name has to be equal to or greater than 2 and less than 16 characters")
    @Pattern(regexp = "^[A-Z]*")
    private String communityName;

    public BaseCommunityDTO() {
    }

    @JsonCreator
    public BaseCommunityDTO(@JsonProperty(required = true) String communityName) {
        this.communityName = communityName;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }


}
