package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.CommunityRepo;
import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.CommunityDTO;
import com.learning.spring.rest.employees.exceptions.Community.DefaultCommunityCanNotBeRemovedException;
import com.learning.spring.rest.employees.exceptions.Community.CommunityAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.Community.CommunityNotFoundByIdException;
import com.learning.spring.rest.employees.mappers.CommunityMapper;
import com.learning.spring.rest.employees.model.Community;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunityServiceImpl implements CommunityService {
    private static final Logger logger = LogManager.getLogger(CommunityServiceImpl.class);

    private CommunityRepo CommunityRepo;
    private CommunityMapper CommunityMapper;

    @Autowired
    public CommunityServiceImpl(CommunityRepo CommunityRepo, @Lazy CommunityMapper CommunityMapper) {
        this.CommunityRepo = CommunityRepo;
        this.CommunityMapper = CommunityMapper;
    }

    @Override
    public BaseCommunityDTO addCommunity(Community comm) throws CommunityAlreadyExistsException {
        Community savedCommunity = null;
        Community community = CommunityRepo.findByCommunityName(comm.getCommunityName());
        if (comm == null) {
            savedCommunity = CommunityRepo.save(comm);
        } else {
            throw new CommunityAlreadyExistsException("Community already exists!");
        }
        BaseCommunityDTO CommunityDTO = CommunityMapper.convertFromCommunityToBaseCommunityDto(savedCommunity);
        return CommunityDTO;
    }

    @Override
    public void deleteCommunityById(int id) throws CommunityNotFoundByIdException, DefaultCommunityCanNotBeRemovedException {
        if (id == 1) {
            throw new DefaultCommunityCanNotBeRemovedException("Default Community can not be removed");
        }
        Community Community = CommunityRepo.findById(id).orElseThrow(() -> new CommunityNotFoundByIdException("Community not found with id=" + id, id));
        CommunityRepo.delete(Community);
    }

    @Override
    public CommunityDTO getCommunityById(int id) throws CommunityNotFoundByIdException {
        Community Community = CommunityRepo.findById(id).orElseThrow(() -> new CommunityNotFoundByIdException("Community not found with id=" + id, id));
        CommunityDTO CommunityDTO = CommunityMapper.convertFromCommunityToCommunityDtoForGet(Community);
        logger.info("Information for Community with id=" + id + ": Name={}", Community.getCommunityName());
        return CommunityDTO;
    }

    @Override
    public Community getDefaultCommunity(int id) {
        return CommunityRepo.getOne(id);

    }

    @Override
    public List<CommunityDTO> getAllCommunities() {
        List<Community> communityRepoAll = CommunityRepo.findAll();
        List<CommunityDTO> communities = communityRepoAll.stream().map(CommunityMapper::convertFromCommunityToCommunityDtoForGet).collect(Collectors.toList());
        return communities;
    }
}
