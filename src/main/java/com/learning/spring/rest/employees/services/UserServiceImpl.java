package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.UserRepo;
import com.learning.spring.rest.employees.dto.UserDTO;
import com.learning.spring.rest.employees.exceptions.user.UserAlreadyExistsException;
import com.learning.spring.rest.employees.mappers.UserMapper;
import com.learning.spring.rest.employees.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.learning.spring.rest.employees.utils.Constants.USER_EXISTS;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);


    private UserMapper userMapper;
    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepo userRepo) {
        this.userMapper = userMapper;
        this.userRepo = userRepo;
    }

    @Override
    public UserDTO getUserById(int id) {
        return null;
    }

    @Override
    @Transactional
    public UserDTO save(UserDTO userDTO) throws UserAlreadyExistsException {
        User userToBeSaved;
        String email = userDTO.getEmail();
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException(USER_EXISTS, email);
        } else {
            userToBeSaved = userMapper.convertFromUserDtoToUser(userDTO);
        }
        User savedUser = userRepo.save(userToBeSaved);
        return userMapper.convertFromUserToUserDto(savedUser);
    }
}
