package com.dimalka.moviescraper.movierepositoryservice.repository;

import com.dimalka.moviescrapercommons.model.repositoryservice.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
