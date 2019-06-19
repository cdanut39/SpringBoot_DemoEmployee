package com.learning.spring.rest.employees.security;

import com.learning.spring.rest.employees.dao.UserRepo;
import com.learning.spring.rest.employees.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user.get());
    }
}
