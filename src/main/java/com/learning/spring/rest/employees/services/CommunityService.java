package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.CommunityDTO;
import com.learning.spring.rest.employees.exceptions.Community.DefaultCommunityCanNotBeRemovedException;
import com.learning.spring.rest.employees.exceptions.Community.CommunityAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.Community.CommunityNotFoundByIdException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.model.Community;

import java.util.List;


public interface CommunityService {

    BaseCommunityDTO addCommunity(Community Community) throws CommunityAlreadyExistsException;

    void deleteCommunityById(int id) throws CommunityNotFoundByIdException, DefaultCommunityCanNotBeRemovedException;

    CommunityDTO getCommunityById(int id) throws EmployeeNotFoundException, CommunityNotFoundByIdException;

    Community getDefaultCommunity(int id);

    List<CommunityDTO> getAllCommunities();

}
