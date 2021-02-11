package com.dimalka.moviescraper.movierepositoryservice.repository;

import com.dimalka.moviescrapercommons.model.repositoryservice.MovieRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieRecord, Integer> {
    MovieRecord findByName(String name);
}
