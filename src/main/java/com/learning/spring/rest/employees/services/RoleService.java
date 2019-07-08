package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.RoleRepo;
import com.learning.spring.rest.employees.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleService {

    private RoleRepo  roleRepo;

    public RoleService(){
    }

    @Autowired
    public RoleService(RoleRepo roleRepo) {
    this.roleRepo=roleRepo;
    }

    private static final Role EMPLOYEE_ROLE = new Role(Role.RoleEnum.EMPLOYEE.name());
    private static final Role MANAGER_ROLE = new Role(Role.RoleEnum.MANAGER.name());


    private static Set<Role> getEmployeeRoleTypes() {
        Set<Role> employeeRoles = new HashSet<>();
        employeeRoles.add(EMPLOYEE_ROLE);
        return employeeRoles;
    }
    private static Set<Role> getManagerRoleTypes() {

        Set<Role> managerRoles = new HashSet<>();
        managerRoles.add(EMPLOYEE_ROLE);
        managerRoles.add(MANAGER_ROLE);
        return managerRoles;
    }

    public Set<Role> getEmpRoles() {
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

    public Set<Role> getManagerRoles() {
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

