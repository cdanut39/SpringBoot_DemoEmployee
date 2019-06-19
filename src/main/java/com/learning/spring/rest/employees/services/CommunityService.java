package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.CommunityRequestDTO;
import com.learning.spring.rest.employees.exceptions.community.CommunityNotFoundByNameException;
import com.learning.spring.rest.employees.exceptions.community.DefaultCommunityCanNotBeRemovedException;
import com.learning.spring.rest.employees.exceptions.community.CommunityAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.community.CommunityNotFoundByIdException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.model.Community;

import java.util.List;


public interface CommunityService {

    BaseCommunityDTO addCommunity(Community community) throws CommunityAlreadyExistsException;

    void deleteCommunityById(int id) throws CommunityNotFoundByIdException, DefaultCommunityCanNotBeRemovedException;

    CommunityRequestDTO getCommunityById(int id) throws EmployeeNotFoundException, CommunityNotFoundByIdException;

    Community getDefaultCommunity(int id);

    List<CommunityRequestDTO> getAllCommunities();

    Community findByName(BaseCommunityDTO communityDTO) throws CommunityNotFoundByNameException;

}
