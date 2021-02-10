package com.dimalka.moviescraper.userservice.service;

import com.dimalka.moviescrapercommons.model.userservice.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity save(User user);
}
