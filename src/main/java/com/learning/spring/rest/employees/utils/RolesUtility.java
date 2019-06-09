package com.learning.spring.rest.employees.utils;

import com.learning.spring.rest.employees.model.Role;

import java.util.HashSet;
import java.util.Set;

public class RolesUtility {
    private static final Role EMPLOYEE_ROLE = new Role(Role.RoleEnum.EMPLOYEE);
    private static final Role MANAGER_ROLE = new Role(Role.RoleEnum.MANAGER);

    public static Set<Role> getEmployeeRoles() {
        Set<Role> employeeRoles = new HashSet<>();
        employeeRoles.add(EMPLOYEE_ROLE);
        return employeeRoles;
    }

    public static Set<Role> getManagerRoles() {

        Set<Role> managerRoles = new HashSet<>();
        managerRoles.add(EMPLOYEE_ROLE);
        managerRoles.add(MANAGER_ROLE);
        return managerRoles;
    }
}
