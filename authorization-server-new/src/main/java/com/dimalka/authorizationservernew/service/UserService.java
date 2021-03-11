package com.dimalka.authorizationservernew.service;

import com.dimalka.authorizationservernew.repository.UserDetailRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.dimalka.authorizationservernew.model.User;

@Service("userService")
public class UserService {

    @Autowired
    private UserDetailRepository userDetailRepository;


    public User registerUser(User user) {

        if (userDetailRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new DuplicateKeyException("Username already exists");
        } else if (userDetailRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateKeyException("Email already exists");
        }
        return userDetailRepository.save(user);
    }
}