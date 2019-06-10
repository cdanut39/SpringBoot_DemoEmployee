package com.learning.spring.rest.employees.utils;

import com.learning.spring.rest.employees.dao.RoleRepo;
import com.learning.spring.rest.employees.model.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RolesUtility {

    public RolesUtility() {
    }

    private static final Role EMPLOYEE_ROLE = new Role(Role.RoleEnum.EMPLOYEE.name());
    private static final Role MANAGER_ROLE = new Role(Role.RoleEnum.MANAGER.name());


    public static Set<Role> getEmployeeRoleTypes() {
        Set<Role> employeeRoles = new HashSet<>();
        employeeRoles.add(EMPLOYEE_ROLE);
        return employeeRoles;
    }
    public static Set<Role> getManagerRoleTypes() {

        Set<Role> managerRoles = new HashSet<>();
        managerRoles.add(EMPLOYEE_ROLE);
        managerRoles.add(MANAGER_ROLE);
        return managerRoles;
    }

    public Set<Role> getEmpRoles(RoleRepo roleRepo) {
        List<String> employeeRolesList = getEmployeeRoleTypes().stream().map(Role::getRoleName).collect(Collectors.toList());
        Set<Role> empRoles = new HashSet<>();
        for (String r : employeeRolesList) {
            Role role = roleRepo.findByRoleName(r);
            if (role != null) {
                empRoles.add(role);
            }
        }
        return empRoles;
    }

    public Set<Role> getManagerRoles(RoleRepo roleRepo) {
        List<String> employeeRolesList = getManagerRoleTypes().stream().map(Role::getRoleName).collect(Collectors.toList());
        Set<Role> managerRoles = new HashSet<>();
        for (String r : employeeRolesList) {
            Role role = roleRepo.findByRoleName(r);
            if (role != null) {
                managerRoles.add(role);
            }
        }
        return managerRoles;
    }
}

