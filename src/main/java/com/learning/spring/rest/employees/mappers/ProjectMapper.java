package com.learning.spring.rest.employees.mappers;

import com.learning.spring.rest.employees.dto.ProjectDTO;
import com.learning.spring.rest.employees.model.Project;

public class ProjectMapper {

    public ProjectDTO convertFromProjectToProjectDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId(project.getProjectId());
        projectDTO.setProjectName(project.getProjectName());
        projectDTO.setManagerFirstName(project.getManager().getFirstName());
        projectDTO.setManagerLastName(project.getManager().getLastName());
        return projectDTO;
    }

    public Project convertFromProjectDTOToProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setProjectId(projectDTO.getProjectId());
        project.setProjectName(projectDTO.getProjectName());
        return project;
    }
}
