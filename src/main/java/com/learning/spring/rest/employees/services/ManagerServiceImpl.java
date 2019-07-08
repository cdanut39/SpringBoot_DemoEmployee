package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.UserRepo;
import com.learning.spring.rest.employees.dto.ManagerDTO;
import com.learning.spring.rest.employees.exceptions.user.UserAlreadyExistsException;
import com.learning.spring.rest.employees.mappers.UserMapper;
import com.learning.spring.rest.employees.model.Manager;
import com.learning.spring.rest.employees.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.learning.spring.rest.employees.utils.Constants.USER_EXISTS;


@Service
public class ManagerServiceImpl implements ManagerService {


    private UserRepo userRepo;
    private RoleService roleService;
    private UserMapper userMapper;

    @Autowired
    public ManagerServiceImpl(UserRepo userRepo, RoleService roleService) {
        this.userRepo = userRepo;
        this.roleService = roleService;
        userMapper = new UserMapper();
    }


    @Override
    @Transactional
    public ManagerDTO save(ManagerDTO managerDTO) throws UserAlreadyExistsException {
        Manager managerToBeSaved;
        String email = managerDTO.getEmail();
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException(USER_EXISTS, email);
        } else {
            managerToBeSaved = userMapper.convertFromManagerDtoTOManager(managerDTO);
            managerToBeSaved.setRoles(roleService.getManagerRoles());
        }
        Manager savedManager = userRepo.save(managerToBeSaved);
        return userMapper.convertFromManagerTOManagerDto(savedManager);
    }

}
