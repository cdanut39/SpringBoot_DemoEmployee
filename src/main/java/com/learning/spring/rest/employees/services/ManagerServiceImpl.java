package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.UserRepo;
import com.learning.spring.rest.employees.dto.ManagerDTO;
import com.learning.spring.rest.employees.dto.UserDTO;
import com.learning.spring.rest.employees.exceptions.custom.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.custom.manager.ManagerNotFoundException;
import com.learning.spring.rest.employees.exceptions.custom.user.UserAlreadyExistsException;
import com.learning.spring.rest.employees.mappers.UserMapper;
import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.model.Manager;
import com.learning.spring.rest.employees.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.learning.spring.rest.employees.utils.Constants.*;


@Service
public class ManagerServiceImpl extends AbstractUserService implements ManagerService {


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
            managerToBeSaved = userMapper.convertFromManagerDtoToManagerSave(managerDTO);
            managerToBeSaved.setRoles(roleService.getManagerRoles());
        }
        Manager savedManager = userRepo.save(managerToBeSaved);
        return userMapper.convertFromManagerToManagerDto(savedManager);
    }

    @Override
    public ManagerDTO findManagerByName(String firstName, String lastName) throws ManagerNotFoundException {
        Manager manager = userRepo.findManagerByName(firstName,lastName).orElseThrow(() -> new ManagerNotFoundException(MANAGER_404));
        return userMapper.convertFromManagerToManagerDto(manager);
    }


    @Override
    public ManagerDTO getUserById(int id) throws ManagerNotFoundException {
        Optional<Manager> manager = userRepo.findManagerById(id);
        if (!manager.isPresent()) {
            throw new ManagerNotFoundException(MANAGER_404);
        }
        return userMapper.convertFromManagerToManagerDto(manager.get());
    }
}
