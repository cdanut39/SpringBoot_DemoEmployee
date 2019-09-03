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
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(email);

        if (user.isPresent()) {
            return new UserPrincipal(user.get());
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}