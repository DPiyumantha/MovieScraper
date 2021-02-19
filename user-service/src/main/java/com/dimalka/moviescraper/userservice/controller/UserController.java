package com.dimalka.moviescraper.userservice.controller;


import com.dimalka.moviescraper.userservice.service.UserServiceImpl;
import com.dimalka.moviescrapercommons.model.userservice.Genre;
import com.dimalka.moviescrapercommons.model.userservice.User;
import com.dimalka.moviescrapercommons.model.userservice.WebSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user-api")
public class UserController {
    /*
    {
    "userEmail":"dimalka@dimalka.lk",
    "username":"dima"
    "firstName":"Dimalka",
    "lastName":"Perera",
    "imgUrl":"url"
}
    * */

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
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id){return userService.getUserById(id);}
    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable int userId){
        userService.deleteUserById(userId);
    }
//    @PatchMapping("/users")
//    public int updateUser(@RequestBody User user){
//        return userService.updateUser(user);
//    }
    @GetMapping("/users/{id}/genres")
    public List<Genre> getGenresOfUser(@PathVariable int id){
        return userService.getGenresOfUser(id);
    }

    @GetMapping("/users/{id}/websites")
    public List<WebSite> getWebsitesOfUser(@PathVariable int id){
        return userService.getWebsitesOfUser(id);
    }



}
