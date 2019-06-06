package com.learning.spring.rest.employees.dao;

import com.learning.spring.rest.employees.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepo extends JpaRepository<Community, Integer> {
    Community findByCommunityName(String name);
}
