package com.dimalka.moviescraper.movierepositoryservice.service;

import com.dimalka.moviescraper.movierepositoryservice.repository.MovieRepository;
import com.dimalka.moviescrapercommons.model.repositoryservice.Genre;
import com.dimalka.moviescrapercommons.model.scrapingservice.Movie;
import com.dimalka.moviescrapercommons.model.repositoryservice.MovieRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {
    @Bean
    @LoadBalanced
    RestTemplate getRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    ;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    Movie_GenreService movie_genreService;

    public List<MovieRecord> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<MovieRecord> saveAllMovies(List<Movie> list) {
        List<MovieRecord> savedMovies = new ArrayList<>();
        list.stream().forEach(movie -> {

            MovieRecord m = saveMovie(movie);
            System.out.println(m);
            if (m != null) savedMovies.add(m);
        });
        return savedMovies;
    }

    private MovieRecord saveMovie(Movie movie) {
        if (getMovieByName(movie.getName()) == null) {
            List<String> genres = movie.getGenres();
            HttpEntity<List<String>> request = new HttpEntity<List<String>>(genres, new HttpHeaders());
            ResponseEntity<Integer[]> res = restTemplate.postForEntity("http://user/genres", request, Integer[].class);
            MovieRecord movieRecord = new MovieRecord();
            movieRecord.setName(movie.getName());
            movieRecord.setImdb(movie.getImdb());
            movieRecord.setImg(movie.getImg());
            movieRecord.setLink(movie.getLink());
            MovieRecord mr = movieRepository.save(movieRecord);
            movie_genreService.saveAllGenres(mr.getId(), Arrays.asList(res.getBody()));
            return mr;
        }
        return null;
    }

    private MovieRecord getMovieByName(String name) {
        return movieRepository.findByName(name);
    }
}
