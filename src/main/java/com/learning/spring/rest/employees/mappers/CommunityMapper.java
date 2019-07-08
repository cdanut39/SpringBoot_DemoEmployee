package com.learning.spring.rest.employees.mappers;

import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.model.Community;
import org.springframework.stereotype.Component;

@Component
public class CommunityMapper {

    public BaseCommunityDTO convertFromCommunityToBaseCommunityDto(Community community) {

        BaseCommunityDTO dto = new BaseCommunityDTO();
        dto.setCommunityId(community.getCommunityId());
        dto.setCommunityName(community.getCommunityName());

        return dto;
    }

    public Community convertFromBaseCommunityDtoToCommunity(BaseCommunityDTO baseCommunityDTO) {

        Community community = new Community(baseCommunityDTO.getCommunityId());
        community.setCommunityName(baseCommunityDTO.getCommunityName());

        return community;
    }

}
