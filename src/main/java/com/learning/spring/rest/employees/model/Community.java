package com.learning.spring.rest.employees.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "community")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int communityId;
    private String communityName;

    @OneToMany(mappedBy = "community", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Employee> employees;

    public Community() {
    }

    public Community(int id) {
        this.communityId = id;
    }

    public Community(int communityId, String communityName) {
        this.communityId = communityId;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }


    @Override
    public String toString() {
        return "community{" +
                "CommunityId=" + communityId +
                ", CommunityName='" + communityName + "}";
    }


}
