package com.dimalka.moviescraper.userservice.repository;

import com.dimalka.moviescrapercommons.model.userservice.Genre;
import com.dimalka.moviescrapercommons.model.userservice.User;
import com.dimalka.moviescrapercommons.model.userservice.WebSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUserEmail(String userEmail);
    public User findByUsername(String username);
    public User findById(String username);

}
