package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.UserRepo;
import com.learning.spring.rest.employees.dto.ManagerDTO;
import com.learning.spring.rest.employees.exceptions.user.UserAlreadyExistsException;
import com.learning.spring.rest.employees.mappers.UserMapper;
import com.learning.spring.rest.employees.model.Manager;
import com.learning.spring.rest.employees.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import static com.learning.spring.rest.employees.utils.Constants.USER_EXISTS;


@Service
public class ManagerServiceImpl implements ManagerService {

    private static final Logger logger = LogManager.getLogger(ManagerServiceImpl.class);

    private UserRepo userRepo;
    private UserMapper userMapper;

    @Autowired
    public ManagerServiceImpl(UserRepo userRepo, @Lazy UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }


    @Override
    public ManagerDTO save(ManagerDTO managerDTO) throws UserAlreadyExistsException {
        Manager managerToBeSaved = null;
        String email = managerDTO.getEmail();
        User user = userRepo.findByEmail(email);
        if (user != null) {
            throw new UserAlreadyExistsException(USER_EXISTS, email);
        } else {
            managerToBeSaved = userMapper.convertFromManagerDtoTOManager(managerDTO);
        }
        Manager savedManager = userRepo.save(managerToBeSaved);
        return userMapper.convertFromManagerTOManagerDto(savedManager);
    }

}
