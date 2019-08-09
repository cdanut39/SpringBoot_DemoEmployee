package com.learning.spring.rest.employees.controller;

import com.learning.spring.rest.employees.dto.ProjectDTO;
import com.learning.spring.rest.employees.exceptions.custom.manager.ManagerNotFoundException;
import com.learning.spring.rest.employees.exceptions.custom.project.ProjectNotValidException;
import com.learning.spring.rest.employees.exceptions.handler.ValidationError;
import com.learning.spring.rest.employees.services.ProjectServiceImpl;
import com.learning.spring.rest.employees.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.learning.spring.rest.employees.utils.BindingResultErrors.getErrors;
import static com.learning.spring.rest.employees.utils.Constants.PROJECT_ADDED;
import static com.learning.spring.rest.employees.utils.Constants.PROJECT_NOT_VALID;

@RestController
@Slf4j
public class ProjectController {

    private ProjectServiceImpl projectService;

    @Autowired
    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }


    /**
     * POST
     */
    @PostMapping("/addProject")
    public ResponseEntity<Response> addProject(@Valid @RequestBody ProjectDTO projectDTO, BindingResult result) throws ProjectNotValidException, ManagerNotFoundException {
        Response response = new Response();
        if (result.hasErrors()) {
            List<ValidationError> errors = getErrors(result);
            log.error("Invalid data for adding new project");
            throw new ProjectNotValidException(PROJECT_NOT_VALID, errors);
        }
        projectService.addProject(projectDTO);
        response.setMessage(PROJECT_ADDED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
