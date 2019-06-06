package com.learning.spring.rest.employees.utils;

import com.learning.spring.rest.employees.dao.CommunityRepo;
import com.learning.spring.rest.employees.model.Community;
import com.learning.spring.rest.employees.model.Employee;

public class EmployeeUtils {

    public void setExistingCommunity(CommunityRepo CommunityRepo, Employee employee) {
        String CommunityName = employee.getCommunity().getCommunityName();
        Community Community = CommunityRepo.findByCommunityName(CommunityName);

        if (Community != null) {
            employee.setCommunity(Community);
        }
    }
}
