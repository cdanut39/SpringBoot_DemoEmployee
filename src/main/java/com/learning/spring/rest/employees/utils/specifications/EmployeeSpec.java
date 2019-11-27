package com.learning.spring.rest.employees.utils.specifications;

import com.learning.spring.rest.employees.model.User;
import com.learning.spring.rest.employees.model.User_;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpec {

    public static Specification<User> getEmployeesByFName(String firstName) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.firstName), firstName);
    }
}
