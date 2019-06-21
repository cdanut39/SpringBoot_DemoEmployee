package com.learning.spring.rest.employees.mappers;

import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.CommunityRequestDTO;
import com.learning.spring.rest.employees.model.Community;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@PropertySource(value = "classpath:config.properties")
public class CommunityMapper {

    @Value("${companyName}")
    private String companyName;

    private UserMapper empMapper;

    @Autowired
    public CommunityMapper(UserMapper empMapper) {
        this.empMapper = empMapper;
    }

    public CommunityRequestDTO convertFromCommunityToCommunityDtoForGet(Community community) {

        CommunityRequestDTO dto = new CommunityRequestDTO();
        dto.setCommunityName(community.getCommunityName());
        dto.setCommunityId(community.getCommunityId());
        dto.setCompanyName(companyName);
        dto.setEmployees(community.getEmployees().stream()
                .map(empMapper::convertFromEmpTOEmployeeDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    public BaseCommunityDTO convertFromCommunityToBaseCommunityDto(Community community) {

        BaseCommunityDTO dto = new BaseCommunityDTO();
        dto.setCommunityName(community.getCommunityName());
        dto.setCommunityId(community.getCommunityId());
        return dto;
    }

    public Community convertFromBaseCommunityDtoToCommunity(BaseCommunityDTO baseCommunityDTO) {

        Community community = new Community(baseCommunityDTO.getCommunityId());
        community.setCommunityName(baseCommunityDTO.getCommunityName());

        return community;
    }

}
