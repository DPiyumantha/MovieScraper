package com.dimalka.moviescraper.scrapingservice.controller;

import com.dimalka.moviescraper.scrapingservice.model.Movie;
import com.dimalka.moviescraper.scrapingservice.service.Scraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    Scraper scraper;

    @GetMapping("/")
    public String greet(){
        System.out.println("Greet");
        return "Hello";
    }

    @GetMapping( value = "/movies", params = {"url"})
    public ResponseEntity<List<Movie>> getAllMovies(@RequestParam("url") String fullUrl) throws IOException, CloneNotSupportedException {
        List<Movie> movies = scraper.getAllMovies(fullUrl);
        if (movies.size() != 0) {
            return new ResponseEntity(movies, HttpStatus.OK);
        }
        return new ResponseEntity(movies, HttpStatus.NOT_FOUND);
    }

}
