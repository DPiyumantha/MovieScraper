package com.dimalka.moviescraper.movierepositoryservice.controller;

import com.dimalka.moviescraper.movierepositoryservice.service.MovieService;
import com.dimalka.moviescrapercommons.model.scrapingservice.Movie;
import com.dimalka.moviescrapercommons.model.repositoryservice.MovieRecord;
import com.dimalka.moviescrapercommons.model.scrapingservice.MoviePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    MovieService movieService;


    @GetMapping("/all-movies")
    public List<MovieRecord> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/movies/{id}")
    public List<MovieRecord> getMoviesOfUser(@PathVariable int id){
        return movieService.getAllMoviesByUserId(id);
    }
    @PostMapping("/movies")
    public List<MovieRecord> saveMovies(@RequestBody MoviePayload payload){
        return movieService.saveAllMovies(payload);
    }
}

