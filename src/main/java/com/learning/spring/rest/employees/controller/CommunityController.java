package com.learning.spring.rest.employees.controller;

import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.exceptions.custom.community.CommunityAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.custom.community.CommunityNotFoundByIdException;
import com.learning.spring.rest.employees.exceptions.custom.community.CommunityNotValidException;
import com.learning.spring.rest.employees.exceptions.custom.community.DefaultCommunityCanNotBeRemovedException;
import com.learning.spring.rest.employees.exceptions.custom.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.handler.ValidationError;
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

import static com.learning.spring.rest.employees.utils.BindingResultErrors.getErrors;
import static com.learning.spring.rest.employees.utils.Constants.*;

@RestController
public class CommunityController {

    private static final Logger logger = LogManager.getLogger(CommunityController.class);

    private CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }


    /**
     * POST
     */
    @PostMapping("/community")
    public ResponseEntity<Response> addCommunity(@Valid @RequestBody BaseCommunityDTO baseCommunityDTO, BindingResult result) throws CommunityNotValidException, CommunityAlreadyExistsException {
        Response response = new Response();
        if (result.hasErrors()) {
            List<ValidationError> errors = getErrors(result);
            logger.error("Invalid data for adding new community");
            throw new CommunityNotValidException(COMMUNITY_NOT_VALID, errors);
        }
        communityService.addCommunity(baseCommunityDTO);
        response.setMessage(COMMUNITY_ADDED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * GET
     */
    @GetMapping("/community/{id}")
    public ResponseEntity<BaseCommunityDTO> getCommunityById(@PathVariable("id") int id) throws CommunityNotFoundByIdException, EmployeeNotFoundException {
        BaseCommunityDTO community = communityService.getCommunityById(id);
        return new ResponseEntity<>(community, HttpStatus.OK);
    }

    @GetMapping("/communities")
    public ResponseEntity<List<BaseCommunityDTO>> getAllCommunities() {

        return new ResponseEntity<>(communityService.getAllCommunities(), HttpStatus.OK);
    }


    /**
     * DELETE
     */
    @DeleteMapping("/community/delete/{id}")
    public ResponseEntity<Response> deleteCommunityById(@PathVariable("id") int id) throws CommunityNotFoundByIdException, DefaultCommunityCanNotBeRemovedException {
        Response response = new Response();
        communityService.deleteCommunityById(id);
        logger.info("Successfully removed the community with id={}", id);
        response.setMessage(COMMUNITY_REMOVED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
