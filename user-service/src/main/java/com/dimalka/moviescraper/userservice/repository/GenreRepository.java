package com.dimalka.moviescraper.userservice.repository;

import com.dimalka.moviescrapercommons.model.userservice.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
