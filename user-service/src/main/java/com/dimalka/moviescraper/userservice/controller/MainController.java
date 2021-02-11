package com.dimalka.moviescraper.userservice.controller;

import com.dimalka.moviescraper.userservice.service.*;
import com.dimalka.moviescrapercommons.model.userservice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    GenreService genreService;
    @Autowired
    User_GenreService user_genreService;
    @Autowired
    WebsiteService websiteService;
    @Autowired
    User_WebsiteService user_websiteService;

    //user
    @PostMapping("/user")
    public ResponseEntity save(@RequestBody User user) {

        return userService.save(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable int userId){
        userService.deleteUserById(userId);
    }


    //Genre
    @PostMapping("/genre")
    public Genre save(@RequestBody Genre genre) {
        return genreService.saveGenre(genre);
    }

    @GetMapping("/genres")
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @PostMapping("/genres")
public List<Integer> getIdsOfGenres(@RequestBody List<String> names){
        return genreService.getIdsOfGenres(names);
    }

    //Website
    @PostMapping("/website")
    public WebSite save(@RequestBody WebSite webSite) {
        return websiteService.saveWebsite(webSite);
    }

    @GetMapping("/websites")
    public List<WebSite> getAllWebsites() {
        return websiteService.getAllWebsites();
    }

    //User_Website
    @PostMapping("/user-website")
    public User_Website save(@RequestBody User_Website user_website) {
        return user_websiteService.saveUserWebsite(user_website);
    }

    @PostMapping("/user-websites")
    public List<User_Website> saveAll(@RequestBody User_Websites user_websites) {
        return saveAllUserWebsites(user_websites);
    }

    @GetMapping("/user-website")
    public List<User_Website> getAllUserWebsite() {
        return user_websiteService.getAllUser_Website();
    }

    @GetMapping("/user-website/{userid}")
    public List<WebSite> getAllWebsitesByUser(@PathVariable int userid) {
        return user_websiteService.getWebsitesByUserId(userid);
    }


    //user_genre
    @PostMapping("/user-genre")
    public User_Genre save(@RequestBody User_Genre user_genre) {
        return user_genreService.saveUserGenre(user_genre);
    }

    @PostMapping("/user-genres")
    public List<User_Genre> saveAll(@RequestBody User_Genres user_genres) {
        return saveAllUserGenre(user_genres);
    }

    @GetMapping("/user-genre")
    public List<User_Genre> getAllUserGenre() {
        return user_genreService.getAllUser_Genre();
    }

    @GetMapping("/user-genre/{userid}")
    public List<Genre> getAllGenresByUser(@PathVariable int userid) {
        return user_genreService.getGenresByUserId(userid);
    }


    private List<User_Genre> saveAllUserGenre(User_Genres user_genres) {
        List<User_Genre> list = new ArrayList<>();
        int userId = user_genres.getUserId();
        List<Integer> genres = user_genres.getGenres();
        genres.forEach(i -> {
            User_Genre user_genre = new User_Genre();
            user_genre.setGenreId(i);
            user_genre.setUserId(userId);
            list.add(user_genreService.saveUserGenre(user_genre));
        });
        return list;
    }

    private List<User_Website> saveAllUserWebsites(User_Websites user_websites) {
        List<User_Website> list = new ArrayList<>();
        int userId = user_websites.getUserId();
        List<Integer> websites = user_websites.getWebsites();
        websites.forEach(i -> {
            User_Website user_website = new User_Website();
            user_website.setWebsiteId(i);
            user_website.setUserId(userId);
            list.add(user_websiteService.saveUserWebsite(user_website));
        });
        return list;
    }

}
