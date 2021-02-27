package com.dimalka.moviescraper.userservice.controller;


import com.dimalka.moviescraper.userservice.service.UserServiceImpl;
import com.dimalka.moviescrapercommons.model.userservice.Genre;
import com.dimalka.moviescrapercommons.model.userservice.User;
import com.dimalka.moviescrapercommons.model.userservice.WebSite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping(value = "/user-api")
public class UserController {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserServiceImpl userService;


    @PostMapping("/users")
    public ResponseEntity save(@RequestBody User user) {

        return userService.save(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/users/user")
    public User getUserByUsername(Principal principal) {
        return userService.getUserByUsername(principal.getName());
    }

    @GetMapping("/users/username")
    public String getUserUsername(Principal principal) {
        return principal.getName();
    }


    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }


    @DeleteMapping("/users/{userId}")
    public ResponseEntity deleteUser(@PathVariable int userId) {

        try {
            userService.deleteUserById(userId);
            return new ResponseEntity(userId, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getLocalizedMessage());
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/users")
    public ResponseEntity updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @CrossOrigin
    @GetMapping("/users/{id}/genres")
    public List<Genre> getGenresOfUser(@PathVariable int id) {
        return userService.getGenresOfUser(id);
    }

    @CrossOrigin
    @GetMapping("/users/{id}/websites")
    public List<WebSite> getWebsitesOfUser(@PathVariable int id) {
        return userService.getWebsitesOfUser(id);
    }


}
