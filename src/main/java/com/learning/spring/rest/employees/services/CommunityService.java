package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.CommunityDTO;
import com.learning.spring.rest.employees.exceptions.community.DefaultCommunityCanNotBeRemovedException;
import com.learning.spring.rest.employees.exceptions.community.CommunityAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.community.CommunityNotFoundByIdException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.model.Community;

import java.util.List;


public interface CommunityService {

    BaseCommunityDTO addCommunity(Community community) throws CommunityAlreadyExistsException;

    void deleteCommunityById(int id) throws CommunityNotFoundByIdException, DefaultCommunityCanNotBeRemovedException;

    CommunityDTO getCommunityById(int id) throws EmployeeNotFoundException, CommunityNotFoundByIdException;

    Community getDefaultCommunity(int id);

    List<CommunityDTO> getAllCommunities();

}
