package com.learning.spring.rest.employees.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.learning.spring.rest.employees.utils.validators.CommunityNameConstraint;

@JsonPropertyOrder(value = {"communityId", "communityName"})
public class BaseCommunityDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int communityId;

    @CommunityNameConstraint
    private String communityName;

    public BaseCommunityDTO() {
    }

    public BaseCommunityDTO(String communityName) {
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
