package com.dimalka.moviescraper.movierepositoryservice.service;

import com.dimalka.moviescraper.movierepositoryservice.repository.Movie_GenreRepository;
import com.dimalka.moviescrapercommons.model.repositoryservice.Movie_Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class Movie_GenreService {
    @Autowired
    Movie_GenreRepository movie_genreRepository;

    public List<Movie_Genre> saveAllGenres(int movieId, List<Integer> ids){
        List<Movie_Genre> savedMovieGenre = new ArrayList<>();
        ids.stream().forEach(id->{
            Movie_Genre mg = new Movie_Genre();
            mg.setGenreId(id);
            mg.setMovieId(movieId);
            savedMovieGenre.add(save(mg));
        });


        return savedMovieGenre;
    }

    public Movie_Genre save(Movie_Genre movie_genre){
        return movie_genreRepository.save(movie_genre);
    }
}
