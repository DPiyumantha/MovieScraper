package com.dimalka.moviescraper.userservice.repository;

import com.dimalka.moviescrapercommons.model.userservice.User_Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_GenreRepository extends JpaRepository<User_Genre, Integer> {
    public List<Integer> findByUserId(int id);
}
