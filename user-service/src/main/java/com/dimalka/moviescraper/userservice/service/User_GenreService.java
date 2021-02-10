package com.dimalka.moviescraper.userservice.service;

import com.dimalka.moviescrapercommons.model.userservice.Genre;
import com.dimalka.moviescrapercommons.model.userservice.User_Genre;
import com.dimalka.moviescraper.userservice.repository.User_GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class User_GenreService {
    @Autowired
    User_GenreRepository user_genreRepository;
    @Autowired
    GenreService genreService;

    public User_Genre saveUserGenre(User_Genre user_genre) {
        return user_genreRepository.save(user_genre);
    }

    public List<User_Genre> getAllUser_Genre() {
        return user_genreRepository.findAll();
    }

    public List<Genre> getGenresByUserId(int id) {
        List<Genre> list = new ArrayList<>();
        List<Integer> genreIds = user_genreRepository.findByUserId(id);
        genreIds.stream().forEach(item -> {
            list.add(genreService.getGenreById(item));
        });
        return list;
    }
}
