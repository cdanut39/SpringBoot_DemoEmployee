package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.ProjectDTO;
import com.learning.spring.rest.employees.exceptions.custom.manager.ManagerNotFoundException;

import java.util.List;

public interface ProjectService {

    ProjectDTO addProject(ProjectDTO project) throws ManagerNotFoundException;

    void removeProject(int projectId);

    ProjectDTO getProjectById(int id);

    ProjectDTO getProjectByName(String name);

    List<ProjectDTO> getAllProjects();

}
