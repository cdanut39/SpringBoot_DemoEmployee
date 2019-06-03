package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.DepartmentRepo;
import com.learning.spring.rest.employees.dao.UserRepo;
import com.learning.spring.rest.employees.dto.AdminDTO;
import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.dto.UserDTO;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.mappers.UserMapper;
import com.learning.spring.rest.employees.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private DepartmentRepo departmentRepo;
    private UserMapper userMapper;
    private DepartmentServiceImpl departmentService;
    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(DepartmentRepo departmentRepo, UserMapper userMapper, DepartmentServiceImpl departmentService, UserRepo userRepo) {
        this.departmentRepo = departmentRepo;
        this.userMapper = userMapper;
        this.departmentService = departmentService;
        this.userRepo = userRepo;
    }

    @Override
    public UserDTO getEmployeeById(int id) throws EmployeeNotFoundException {
        return null;
    }

    @Override
    public <T extends UserDTO> UserDTO save(T user) {
        T userType = null;
        if (user.getUserType().toString().equals("EMPLOYEE")) {
            userType = (T) saveEmployee(user);
        } else if (user.getUserType().toString().equals("ADMIN")) {
            userType = (T) saveAdmin(user);
        } else if (user.getUserType().toString().equals("USER")) {
            userType = (T) saveUser(user);
        } else return new UserDTO();
        return userType;
    }


    public <T extends UserDTO, T1 extends User> UserDTO saveEmployee(T emp) {

        T1 user = userMapper.convertFromEmpDtoTOEmployee((EmployeeDTO) emp);
        User savedEmp = userRepo.save(user);
        UserDTO userDTO = userMapper.convertFromUserToUserDto(savedEmp);
//        logger.info("Employee with id {} and name {} was added successfully!", emp.getId(), emp.getName());

        return userDTO;
    }

    public <T extends UserDTO> UserDTO saveAdmin(T emp) {

        User user = userMapper.convertFromAdminDtoTOAdmin((AdminDTO) emp);
        User savedEmp = userRepo.save(user);
        UserDTO userDTO = userMapper.convertFromUserToUserDto(savedEmp);
//        logger.info("Employee with id {} and name {} was added successfully!", emp.getId(), emp.getName());

        return userDTO;
    }

    public <T extends UserDTO> UserDTO saveUser(T emp) {

        User user = userMapper.convertFromUserDtoToUser(emp);
        User savedEmp = userRepo.save(user);
        UserDTO userDTO = userMapper.convertFromUserToUserDto(savedEmp);
//        logger.info("Employee with id {} and name {} was added successfully!", emp.getId(), emp.getName());

        return userDTO;
    }

}
