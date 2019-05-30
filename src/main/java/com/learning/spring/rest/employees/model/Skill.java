package com.learning.spring.rest.employees.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int skillID;
    private SkillCategories category;
    private String skillName;

    private enum SkillCategories {
        CODING, COMMUNICATION, OS, SECURITY, DATABASE, MANAGEMENT, ORGANIZATIONAL
    }

    public Skill() {
    }

    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    public SkillCategories getCategory() {
        return category;
    }

    public void setCategory(SkillCategories category) {
        this.category = category;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}

