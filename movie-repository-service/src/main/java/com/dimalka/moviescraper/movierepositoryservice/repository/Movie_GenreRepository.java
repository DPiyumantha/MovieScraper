package com.dimalka.moviescraper.movierepositoryservice.repository;

import com.dimalka.moviescrapercommons.model.repositoryservice.Movie_Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Movie_GenreRepository extends JpaRepository<Movie_Genre, Integer> {
}
