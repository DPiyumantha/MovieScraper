package com.dimalka.authorizationservernew.service;

import com.dimalka.authorizationservernew.repository.UserDetailRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimalka.authorizationservernew.model.User;
@Service("userService")
public class UserService {

    @Autowired
    private UserDetailRepository userDetailRepository;


    public User registerUser(User user){
        return userDetailRepository.save(user);
    }
}
