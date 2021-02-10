package com.dimalka.moviescraper.movierepositoryservice.service;

import com.dimalka.moviescraper.movierepositoryservice.repository.MovieRepository;
import com.dimalka.moviescrapercommons.model.repositoryservice.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public List<Movie> saveAllMovies(List<Movie> list){
        List<Movie> savedMovies = new ArrayList<>();
         list.stream().forEach(movie->{
            Movie m = saveMovie(movie);
            if(m!=null)savedMovies.add(m);
        });
         return savedMovies;
    }

    private Movie saveMovie(Movie movie){
        if(getMovieByName(movie.getName())==null)
        return movieRepository.save(movie);
        return null;
    }

    private Movie getMovieByName(String name){
        return movieRepository.findByName(name);
    }
}
