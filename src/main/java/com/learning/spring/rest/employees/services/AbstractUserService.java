package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.UserRepo;
import com.learning.spring.rest.employees.exceptions.custom.user.UserNotFoundException;
import com.learning.spring.rest.employees.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.learning.spring.rest.employees.utils.Constants.USER_404;

@Slf4j
public abstract class AbstractUserService implements UserService {

    private UserRepo userRepo;

    public AbstractUserService() {
    }

    //in an abstract class setter injection must be used
    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public void removeUser(int id) throws UserNotFoundException {
        Optional<User> user = userRepo.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException(USER_404);
        } else {
            User emp = user.get();
            userRepo.delete(emp);
            log.info("Successfully removed user with id={},{}", emp.getUserId(), emp.getFirstName());
        }
    }

}
