package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.CommunityRepo;
import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.CommunityRequestDTO;
import com.learning.spring.rest.employees.exceptions.community.CommunityAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.community.CommunityNotFoundByIdException;
import com.learning.spring.rest.employees.exceptions.community.CommunityNotFoundByNameException;
import com.learning.spring.rest.employees.exceptions.community.DefaultCommunityCanNotBeRemovedException;
import com.learning.spring.rest.employees.mappers.CommunityMapper;
import com.learning.spring.rest.employees.model.Community;
import com.learning.spring.rest.employees.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Community savedCommunity;
        Optional<Community> community = communityRepo.findByCommunityName(comm.getCommunityName());
        if (community.isPresent()) {
            throw new CommunityAlreadyExistsException("Community already exists!");
        } else {
            savedCommunity = communityRepo.save(comm);
        }
        return communityMapper.convertFromCommunityToBaseCommunityDto(savedCommunity);
    }

    @Override
    public void deleteCommunityById(int id) throws CommunityNotFoundByIdException, DefaultCommunityCanNotBeRemovedException {
        if (id == Constants.DEFAULT_COMMUNITY) {
            throw new DefaultCommunityCanNotBeRemovedException("Default community can not be removed");
        }
        Community community = communityRepo.findById(id).orElseThrow(() -> new CommunityNotFoundByIdException("community not found with id=" + id, id));
        communityRepo.delete(community);
    }

    @Override
    public CommunityRequestDTO getCommunityById(int id) throws CommunityNotFoundByIdException {
        Community community = communityRepo.findById(id).orElseThrow(() -> new CommunityNotFoundByIdException("community not found with id=" + id, id));
        CommunityRequestDTO communityRequestDTO = communityMapper.convertFromCommunityToCommunityDtoForGet(community);
        logger.info("Information for community with id=" + id + ": Name={}", community.getCommunityName());
        return communityRequestDTO;
    }

    @Override
    public Community getDefaultCommunity(int id) {
        return communityRepo.getOne(id);

    }

    @Override
    public List<CommunityRequestDTO> getAllCommunities() {
        List<Community> communityRepoAll = communityRepo.findAll();
        return communityRepoAll.stream().map(communityMapper::convertFromCommunityToCommunityDtoForGet).collect(Collectors.toList());
    }

    @Override
    public Community findByName(BaseCommunityDTO communityDTO) throws CommunityNotFoundByNameException {
        return communityRepo.findByCommunityName(communityDTO.getCommunityName()).orElseThrow(
                () -> new CommunityNotFoundByNameException("Community not found with name " + communityDTO.getCommunityName(), communityDTO.getCommunityName()));
    }

    public Community findByName(String communityName)   {
        return communityRepo.findByCommunityName(communityName).orElse(null);
    }
}
