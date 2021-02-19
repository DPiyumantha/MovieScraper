package com.dimalka.moviescraper.movierepositoryservice.controller;

import com.dimalka.moviescraper.movierepositoryservice.service.MovieService;
import com.dimalka.moviescrapercommons.model.scrapingservice.Movie;
import com.dimalka.moviescrapercommons.model.repositoryservice.MovieRecord;
import com.dimalka.moviescrapercommons.model.scrapingservice.MoviePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MainController {
    @Autowired
    MovieService movieService;

    @GetMapping("/movies")
    public List<MovieRecord> getAllMovies(){
        return movieService.getAllMovies();
    }

    @PostMapping("/movies")
    public List<MovieRecord> saveMovies(@RequestBody MoviePayload payload){
        return movieService.saveAllMovies(payload);
    }
}

