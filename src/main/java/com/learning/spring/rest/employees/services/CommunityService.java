package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.exceptions.custom.community.CommunityAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.custom.community.CommunityNotFoundByIdException;
import com.learning.spring.rest.employees.exceptions.custom.community.CommunityNotFoundByNameException;
import com.learning.spring.rest.employees.exceptions.custom.community.DefaultCommunityCanNotBeRemovedException;
import com.learning.spring.rest.employees.exceptions.custom.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.model.Community;

import java.util.List;


public interface CommunityService {

    BaseCommunityDTO addCommunity(BaseCommunityDTO baseCommunityDTO) throws CommunityAlreadyExistsException;

    void deleteCommunityById(int id) throws CommunityNotFoundByIdException, DefaultCommunityCanNotBeRemovedException;

    BaseCommunityDTO getCommunityById(int id) throws EmployeeNotFoundException, CommunityNotFoundByIdException;

    Community getDefaultCommunity(int id);

    List<BaseCommunityDTO> getAllCommunities();

    Community findByName(String communityName) throws CommunityNotFoundByNameException;

}
