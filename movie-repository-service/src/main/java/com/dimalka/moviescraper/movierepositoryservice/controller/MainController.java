package com.dimalka.moviescraper.movierepositoryservice.controller;

import com.dimalka.moviescraper.movierepositoryservice.service.MovieService;
import com.dimalka.moviescrapercommons.model.repositoryservice.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {
    @Autowired
    MovieService movieService;

    @GetMapping("/movies")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    @PostMapping("/movies")
    public List<Movie> saveMovies(@RequestBody List<Movie> movies){
        return movieService.saveAllMovies(movies);
    }
}
