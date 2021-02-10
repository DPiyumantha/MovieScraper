package com.dimalka.moviescraper.userservice.service;

import com.dimalka.moviescraper.userservice.repository.UserRepository;
import com.dimalka.moviescrapercommons.model.userservice.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public ResponseEntity save(User user) {
        if(getUserByUserEmail(user.getUserEmail())==null)
        return new ResponseEntity(userRepository.save(user), HttpStatus.OK);
        return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUserEmail(String userEmail){
        return userRepository.findByUserEmail(userEmail);
    }

    public int updateUser(User user){
        return userRepository.updateUser(user.getId(),user.getUserEmail(), user.getFirstName(), user.getLastName(), user.getImgUrl());
    }

    public void deleteUserById(int id){
        userRepository.deleteById(id);
    }
}
