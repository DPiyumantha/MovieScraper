package com.dimalka.moviescraper.userservice.service;

import com.dimalka.moviescraper.userservice.repository.UserRepository;
import com.dimalka.moviescrapercommons.model.errorhandler.ErrorMsg;
import com.dimalka.moviescrapercommons.model.userservice.Genre;
import com.dimalka.moviescrapercommons.model.userservice.User;
import com.dimalka.moviescrapercommons.model.userservice.WebSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity save(User user) {
        if (getUserByUserEmail(user.getUserEmail()) == null)
            return new ResponseEntity(userRepository.save(user), HttpStatus.OK);
        return new ResponseEntity(new ErrorMsg("Email address "+user.getUserEmail()+" already exists."), HttpStatus.CONFLICT);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }

    public ResponseEntity updateUser(User user) {
        User userOriginal = getUserById(user.getId());
        User updatedUser = user;
        if(getUserByUserEmail(updatedUser.getUserEmail())==null
                || getUserByUserEmail(updatedUser.getUserEmail()).getId()==userOriginal.getId()){
            List<Genre> genres = new ArrayList<>();
            List<WebSite> websites = new ArrayList<>();
            userOriginal.setFirstName(updatedUser.getFirstName());
            userOriginal.setLastName(updatedUser.getLastName());
            userOriginal.setUserEmail(updatedUser.getUserEmail());
            genres.addAll(updatedUser.getGenres());
            websites.addAll(updatedUser.getWebSites());
            userOriginal.setGenres(genres);
            userOriginal.setWebSites(websites);
            return new ResponseEntity(userRepository.save(userOriginal), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorMsg("Email address"+user.getUserEmail() +" already registered for another user"), HttpStatus.BAD_REQUEST);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public List<Genre> getGenresOfUser(int id) {
        return getUserById(id).getGenres();
    }

    public List<WebSite> getWebsitesOfUser(int id) {
        return getUserById(id).getWebSites();
    }

    public User getUserByUsername(String name) {
        return userRepository.findByUsername(name);
    }
}
