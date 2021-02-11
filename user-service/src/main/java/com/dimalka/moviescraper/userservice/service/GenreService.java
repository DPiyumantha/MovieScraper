package com.dimalka.moviescraper.userservice.service;

import com.dimalka.moviescrapercommons.model.userservice.Genre;
import com.dimalka.moviescraper.userservice.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    public Genre saveGenre(Genre genre){
        return genreRepository.save(genre);
    }

    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }

    public Genre getGenreById(int id){
        return genreRepository.findById(id).orElse(null);
    }

    public Genre getGenreByName(String name){return genreRepository.findByName(name).orElse(null);}

    public List<Integer> getIdsOfGenres(List<String> names) {
        List<Integer> ids = new ArrayList<>();

        names.stream().forEach(name->{
            Genre g= getGenreByName(name);
            if(g!=null)ids.add(g.getId());
        });


        return ids;
    }
}
