package com.dimalka.moviescraper.userservice.controller;

import com.dimalka.moviescraper.userservice.service.GenreService;
import com.dimalka.moviescrapercommons.model.userservice.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/genre-api")
public class GenreController {

    @Autowired
    GenreService genreService;

    @PostMapping("/genres")
    public Genre save(@RequestBody Genre genre) {
        return genreService.saveGenre(genre);
    }

    @GetMapping("/genres")
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/genreids")
    public List<Integer> getIdsOfGenres(@RequestBody List<String> names){
        return genreService.getIdsOfGenres(names);
    }

    @PostMapping("/genreobjs")
    public List<Genre> getGenresByNames(@RequestBody String[] names){
        return genreService.getGenresByNames(names);
    }

}
