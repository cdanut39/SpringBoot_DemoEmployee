package com.learning.spring.rest.employees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@DiscriminatorValue("Employee")
public class Employee extends User {

    @JsonProperty(value = "firstDay")
    private LocalDate startDate;
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "community_ID")
    private Community community;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "project_ID")
    private Project project;

    @Transient
    private String communityName;

    @Transient
    private String projectName;

    public Employee() {
    }

    public Employee(Integer userId, String firstName, String lastName, Gender sex,
                    Long phoneNumber, String email, String password,
                    LocalDate startDate, Community community, Project project, String communityName, String projectName) {
        super(userId, firstName, lastName, sex, phoneNumber, email, password);


        this.startDate = startDate;
        this.community = community;
        this.project = project;
        this.communityName = communityName;
        this.projectName = projectName;
    }

    public Employee(LocalDate startDate, Community community, Project project, String communityName, String projectName) {
        this.startDate = startDate;
        this.community = community;
        this.project = project;
        this.communityName = communityName;
        this.projectName = projectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public void setCommunityName(Employee employee) {
        this.communityName = employee.getCommunity().getCommunityName();
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getCommunityName() {
        return communityName;
    }

}
