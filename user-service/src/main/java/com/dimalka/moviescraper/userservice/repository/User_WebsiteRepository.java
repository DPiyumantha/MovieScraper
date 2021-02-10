package com.dimalka.moviescraper.userservice.repository;

import com.dimalka.moviescrapercommons.model.userservice.User_Website;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_WebsiteRepository extends JpaRepository<User_Website, Integer> {
    public List<User_Website> findAllByUserId(int id);
}
