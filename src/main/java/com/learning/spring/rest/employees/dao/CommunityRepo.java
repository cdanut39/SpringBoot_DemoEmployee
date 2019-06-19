package com.learning.spring.rest.employees.dao;

import com.learning.spring.rest.employees.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityRepo extends JpaRepository<Community, Integer> {
    Optional<Community> findByCommunityName(String name);
}
