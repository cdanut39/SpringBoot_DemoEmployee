package com.learning.spring.rest.employees.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int projectId;
    private String projectName;
    private String managerFirstName;
    private String managerLastName;

}
