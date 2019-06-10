package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.CommunityRepo;
import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.CommunityDTO;
import com.learning.spring.rest.employees.exceptions.community.DefaultCommunityCanNotBeRemovedException;
import com.learning.spring.rest.employees.exceptions.community.CommunityAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.community.CommunityNotFoundByIdException;
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

    private CommunityRepo communityRepo;
    private CommunityMapper communityMapper;

    @Autowired
    public CommunityServiceImpl(CommunityRepo communityRepo, @Lazy CommunityMapper communityMapper) {
        this.communityRepo = communityRepo;
        this.communityMapper = communityMapper;
    }

    @Override
    public BaseCommunityDTO addCommunity(Community comm) throws CommunityAlreadyExistsException {
        Community savedCommunity = null;
        Community community = communityRepo.findByCommunityName(comm.getCommunityName());
        if (community == null) {
            savedCommunity = communityRepo.save(comm);
        } else {
            throw new CommunityAlreadyExistsException("Community already exists!");
        }
        BaseCommunityDTO communityDTO = communityMapper.convertFromCommunityToBaseCommunityDto(savedCommunity);
        return communityDTO;
    }

    @Override
    public void deleteCommunityById(int id) throws CommunityNotFoundByIdException, DefaultCommunityCanNotBeRemovedException {
        if (id == 1) {
            throw new DefaultCommunityCanNotBeRemovedException("Default community can not be removed");
        }
        Community community = communityRepo.findById(id).orElseThrow(() -> new CommunityNotFoundByIdException("community not found with id=" + id, id));
        communityRepo.delete(community);
    }

    @Override
    public CommunityDTO getCommunityById(int id) throws CommunityNotFoundByIdException {
        Community community = communityRepo.findById(id).orElseThrow(() -> new CommunityNotFoundByIdException("community not found with id=" + id, id));
        CommunityDTO communityDTO = communityMapper.convertFromCommunityToCommunityDtoForGet(community);
        logger.info("Information for community with id=" + id + ": Name={}", community.getCommunityName());
        return communityDTO;
    }

    @Override
    public Community getDefaultCommunity(int id) {
        return communityRepo.getOne(id);

    }

    @Override
    public List<CommunityDTO> getAllCommunities() {
        List<Community> communityRepoAll = communityRepo.findAll();
        List<CommunityDTO> communities = communityRepoAll.stream().map(communityMapper::convertFromCommunityToCommunityDtoForGet).collect(Collectors.toList());
        return communities;
    }
}
