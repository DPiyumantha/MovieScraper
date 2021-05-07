package com.dimalka.moviescraper.scrapingservice.controller;

import com.dimalka.moviescraper.scrapingservice.schedules.ScheduledScraper;
import com.dimalka.moviescraper.scrapingservice.service.Scraper;
import com.dimalka.moviescrapercommons.model.scrapingservice.Movie;
import com.dimalka.moviescrapercommons.model.userservice.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class MainController {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    Scraper scraper;
    @Autowired
    ScheduledScraper scheduledScraper;

    @PostMapping("/scrape")
    public String scrapeForUser(@RequestBody User user){
        System.out.println("In /scraper");
        System.out.println(user);
        new Thread(() ->  scheduledScraper.scrapeForUser(user)).start();
        log.info("scraper started for user "+user.getUsername());
        return "Scraper started";
    }

    @GetMapping( value = "/movies", params = {"url"})
    public ResponseEntity<List<Movie>> getAllMovies(@RequestParam("url") String fullUrl) throws IOException, CloneNotSupportedException {
        List<Movie> movies = scraper.getAllMovies(fullUrl);
        if (movies.size() != 0) {
            return new ResponseEntity(movies, HttpStatus.OK);
        }
        log.info("No movies on "+fullUrl);
        return new ResponseEntity(movies, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public String sayHello(){
        return "Hello";
    }


}
