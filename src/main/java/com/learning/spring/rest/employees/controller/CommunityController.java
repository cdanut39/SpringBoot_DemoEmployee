package com.learning.spring.rest.employees.controller;

import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.CommunityDTO;
import com.learning.spring.rest.employees.exceptions.community.CommunityAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.community.CommunityNotFoundByIdException;
import com.learning.spring.rest.employees.exceptions.community.CommunityNotValidException;
import com.learning.spring.rest.employees.exceptions.community.DefaultCommunityCanNotBeRemovedException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions_handler.ValidationError;
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

import static com.learning.spring.rest.employees.utils.Constants.COMMUNITY_ADDED;
import static com.learning.spring.rest.employees.utils.Constants.COMMUNITY_REMOVED;

@RestController
public class CommunityController {

    private static final Logger logger = LogManager.getLogger(CommunityController.class);


    private CommunityService communityService;
    private CommunityMapper communityMapper;
    private Response response;

    @Autowired
    public CommunityController(CommunityService communityService, CommunityMapper communityMapper, Response response) {
        this.communityService = communityService;
        this.communityMapper = communityMapper;
        this.response = response;
    }

    @PostMapping("/community")
    public ResponseEntity<Response> addCommunity(@Valid @RequestBody BaseCommunityDTO baseCommunityDTO, BindingResult result) throws CommunityNotValidException, CommunityAlreadyExistsException {
        if (result.hasErrors()) {

            List<ValidationError> fieldErrors = result.getFieldErrors().stream()
                    .map(e -> new ValidationError(e.getField(), e.getRejectedValue().toString(), e.getDefaultMessage()))
                    .collect(Collectors.toList());
            logger.error("Invalid data for adding new community");
            throw new CommunityNotValidException("community data not valid", fieldErrors);

        }
        Community community = communityMapper.convertFromBaseCommunityDtoToCommunity(baseCommunityDTO);
        communityService.addCommunity(community);
        response.setMessage(COMMUNITY_ADDED);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping("/community/delete/{id}")
    public ResponseEntity<Response> deleteCommunityById(@PathVariable("id") int id) throws CommunityNotFoundByIdException, DefaultCommunityCanNotBeRemovedException {
        communityService.deleteCommunityById(id);
        logger.info("Successfully removed the community with id={}", id);
        response.setMessage(COMMUNITY_REMOVED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/community/{id}")
    public ResponseEntity<CommunityDTO> getCommunityById(@PathVariable("id") int id) throws CommunityNotFoundByIdException, EmployeeNotFoundException {
        CommunityDTO community = communityService.getCommunityById(id);
        return new ResponseEntity<>(community, HttpStatus.OK);
    }

    @GetMapping("/communities")
    public ResponseEntity<List<CommunityDTO>> getAllCommunitys() {

        return new ResponseEntity<>(communityService.getAllCommunities(), HttpStatus.OK);
    }
}
