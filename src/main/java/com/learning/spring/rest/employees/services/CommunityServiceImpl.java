package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.CommunityRepo;
import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.exceptions.NoResultsException;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.learning.spring.rest.employees.utils.Constants.NO_RESULTS;

@Service
public class CommunityServiceImpl implements CommunityService {
    private static final Logger logger = LogManager.getLogger(CommunityServiceImpl.class);

    private CommunityRepo communityRepo;
    private CommunityMapper communityMapper;

    @Autowired
    public CommunityServiceImpl(CommunityRepo communityRepo) {
        this.communityRepo = communityRepo;
        communityMapper=new CommunityMapper();
    }

    @Override
    @Transactional
    public BaseCommunityDTO addCommunity(BaseCommunityDTO baseCommunityDTO) throws CommunityAlreadyExistsException {
        Community comm = communityMapper.convertFromBaseCommunityDtoToCommunity(baseCommunityDTO);
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
    public BaseCommunityDTO getCommunityById(int id) throws CommunityNotFoundByIdException {
        Community community = communityRepo.findById(id).orElseThrow(() -> new CommunityNotFoundByIdException("community not found with id=" + id, id));
        BaseCommunityDTO baseCommunityDTO = communityMapper.convertFromCommunityToBaseCommunityDto(community);
        logger.info("Information for community with id=" + id + ": Name={}", community.getCommunityName());
        return baseCommunityDTO;
    }

    @Override
    public Community getDefaultCommunity(int id) {
        return communityRepo.getOne(id);

    }

    @Override
    public List<BaseCommunityDTO> getAllCommunities() {
        List<Community> communityRepoAll = communityRepo.findAll();
        return communityRepoAll.stream().map(communityMapper::convertFromCommunityToBaseCommunityDto).collect(Collectors.toList());
    }


    @Override
    public Community findByName(String communityName) throws CommunityNotFoundByNameException {
        return communityRepo.findByCommunityName(communityName).orElseThrow(
                () -> new CommunityNotFoundByNameException("Community not found with name " + communityName, communityName));
    }

    /**
     * Only used for search feature due to exception message, which is custom
     *
     * @param communityName
     * @return
     * @throws NoResultsException
     */

    public BaseCommunityDTO searchByName(String communityName) throws NoResultsException {
        Community community = communityRepo.findByCommunityName(communityName).orElse(null);
        if (community == null) {
            throw new NoResultsException(NO_RESULTS);
        } else return communityMapper.convertFromCommunityToBaseCommunityDto(community);
    }

    @Override
    public void deleteCommunityById(int id) throws CommunityNotFoundByIdException, DefaultCommunityCanNotBeRemovedException {
        if (id == Constants.DEFAULT_COMMUNITY) {
            throw new DefaultCommunityCanNotBeRemovedException("Default community can not be removed");
        }
        Community community = communityRepo.findById(id).orElseThrow(() -> new CommunityNotFoundByIdException("community not found with id=" + id, id));
        communityRepo.delete(community);
    }
}
