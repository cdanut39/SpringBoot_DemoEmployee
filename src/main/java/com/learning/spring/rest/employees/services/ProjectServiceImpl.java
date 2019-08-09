package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.ProjectRepo;
import com.learning.spring.rest.employees.dto.ProjectDTO;
import com.learning.spring.rest.employees.exceptions.custom.manager.ManagerNotFoundException;
import com.learning.spring.rest.employees.mappers.ProjectMapper;
import com.learning.spring.rest.employees.mappers.UserMapper;
import com.learning.spring.rest.employees.model.Manager;
import com.learning.spring.rest.employees.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectMapper projectMapper;
    private ProjectRepo projectRepo;
    private UserMapper userMapper;
    private ManagerServiceImpl managerService;

    @Autowired
    public ProjectServiceImpl(ProjectRepo projectRepo, ManagerServiceImpl managerService) {
        this.projectRepo = projectRepo;
        this.managerService = managerService;
        this.projectMapper = new ProjectMapper();
        this.userMapper = new UserMapper();
    }

    @Override
    @Transactional
    public ProjectDTO addProject(ProjectDTO projectDTO) throws ManagerNotFoundException {

        Project projectToBeSaved = projectMapper.convertFromProjectDTOToProject(projectDTO);
        Manager manager = userMapper.convertFromManagerDtoToManager(managerService.findManagerByName(projectDTO.getManagerFirstName(), projectDTO.getManagerLastName()));
        projectToBeSaved.setManager(manager);
        Project savedProject = projectRepo.save(projectToBeSaved);
        return projectMapper.convertFromProjectToProjectDTO(savedProject);
    }

    @Override
    public void removeProject(int projectId) {

    }

    @Override
    public ProjectDTO getProjectById(int id) {
        return null;
    }

    @Override
    public ProjectDTO getProjectByName(String name) {
        return null;
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return null;
    }
}
