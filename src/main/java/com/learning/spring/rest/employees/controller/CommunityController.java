package com.learning.spring.rest.employees.controller;

import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.CommunityDTO;
import com.learning.spring.rest.employees.exceptions.Community.DefaultCommunityCanNotBeRemovedException;
import com.learning.spring.rest.employees.exceptions.Community.CommunityAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.Community.CommunityNotFoundByIdException;
import com.learning.spring.rest.employees.exceptions.Community.CommunityNotValidException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptionsHandler.ValidationError;
import com.learning.spring.rest.employees.mappers.CommunityMapper;
import com.learning.spring.rest.employees.model.Community;
import com.learning.spring.rest.employees.services.CommunityService;
import com.learning.spring.rest.employees.utils.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.learning.spring.rest.employees.utils.Constants.Community_ADDED;
import static com.learning.spring.rest.employees.utils.Constants.Community_REMOVED;

@RestController
public class CommunityController {

    private static final Logger logger = LogManager.getLogger(CommunityController.class);


    private CommunityService communityService;
    private CommunityMapper CommunityMapper;
    private Response response;

    @Autowired
    public CommunityController(CommunityService CommunityService, CommunityMapper CommunityMapper, Response response) {
        this.communityService = CommunityService;
        this.CommunityMapper = CommunityMapper;
        this.response = response;
    }

    @PostMapping("/community")
    public ResponseEntity<Response> addCommunity(@Valid @RequestBody BaseCommunityDTO baseCommunityDTO, BindingResult result) throws CommunityNotValidException, CommunityAlreadyExistsException {
        if (result.hasErrors()) {

            List<ValidationError> fieldErrors = result.getFieldErrors().stream()
                    .map(e -> new ValidationError(e.getField(), e.getRejectedValue().toString(), e.getDefaultMessage()))
                    .collect(Collectors.toList());
            logger.error("Invalid data for adding new Community");
            throw new CommunityNotValidException("Community data not valid", fieldErrors);

        }
        Community Community = CommunityMapper.convertFromBaseCommunityDtoToCommunity(baseCommunityDTO);
        BaseCommunityDTO CommunityDTO = communityService.addCommunity(Community);
        response.setMessage(Community_ADDED);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping("/community/delete/{id}")
    public ResponseEntity<Response> deleteCommunityById(@PathVariable("id") int id) throws CommunityNotFoundByIdException, DefaultCommunityCanNotBeRemovedException {
        communityService.deleteCommunityById(id);
        logger.info("Successfully removed the Community with id={}", id);
        response.setMessage(Community_REMOVED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/community/{id}")
    public ResponseEntity<CommunityDTO> getCommunityById(@PathVariable("id") int id) throws CommunityNotFoundByIdException, EmployeeNotFoundException {
        CommunityDTO Community = communityService.getCommunityById(id);
        return new ResponseEntity<>(Community, HttpStatus.OK);
    }

    @GetMapping("/communities")
    public ResponseEntity<List<CommunityDTO>> getAllCommunitys() {

        return new ResponseEntity<>(communityService.getAllCommunities(), HttpStatus.OK);
    }
}
