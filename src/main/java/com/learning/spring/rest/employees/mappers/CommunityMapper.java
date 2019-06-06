package com.learning.spring.rest.employees.mappers;

import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.CommunityDTO;
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

    public CommunityDTO convertFromCommunityToCommunityDtoForGet(Community Community) {

        CommunityDTO dto = new CommunityDTO();
        dto.setCommunityName(Community.getCommunityName());
        dto.setCommunityId(Community.getCommunityId());
        dto.setCompanyName(companyName);
        dto.setEmployees(Community.getEmployees().stream()
                .map(empMapper::convertFromEmpTOEmployeeDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    public BaseCommunityDTO convertFromCommunityToBaseCommunityDto(Community Community) {

        BaseCommunityDTO dto = new BaseCommunityDTO();
        dto.setCommunityName(Community.getCommunityName());
        dto.setCommunityId(Community.getCommunityId());
        return dto;
    }

    public Community convertFromBaseCommunityDtoToCommunity(BaseCommunityDTO baseCommunityDTO) {

        Community Community = new Community();
        Community.setCommunityName(baseCommunityDTO.getCommunityName());

        return Community;
    }

}
