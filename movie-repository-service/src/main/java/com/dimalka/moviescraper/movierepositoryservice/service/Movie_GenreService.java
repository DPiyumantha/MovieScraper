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
        System.out.println("Saving all genres");
        List<Movie_Genre> savedMovieGenre = new ArrayList<>();
        ids.stream().forEach(id->{
            Movie_Genre mg = new Movie_Genre();
            mg.setGenreId(id);
            mg.setMovieId(movieId);
            Movie_Genre saved = this.save(mg);
            savedMovieGenre.add(saved);
        });
        System.out.println("Saved genres");
        System.out.println(savedMovieGenre);

        return savedMovieGenre;
    }

    public Movie_Genre save(Movie_Genre movie_genre){
        System.out.println("Saving genre for"+ movie_genre.getMovieId());
        System.out.println("Here");
        return movie_genreRepository.save(movie_genre);
    }
}
