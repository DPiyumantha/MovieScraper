package com.dimalka.moviescraper.userservice.service;

import com.dimalka.moviescraper.userservice.repository.UserRepository;
import com.dimalka.moviescrapercommons.model.userservice.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public User save(User user) {
        return userRepository.save(user);
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
