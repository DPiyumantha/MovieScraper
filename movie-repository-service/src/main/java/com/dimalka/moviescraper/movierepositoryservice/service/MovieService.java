package com.dimalka.moviescraper.movierepositoryservice.service;

import com.dimalka.moviescraper.movierepositoryservice.repository.MovieRepository;
import com.dimalka.moviescrapercommons.model.repositoryservice.Genre;
import com.dimalka.moviescrapercommons.model.scrapingservice.Movie;
import com.dimalka.moviescrapercommons.model.repositoryservice.MovieRecord;
import com.dimalka.moviescrapercommons.model.scrapingservice.MoviePayload;
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
import java.util.Map;

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

    public List<MovieRecord> saveAllMovies(MoviePayload payload) {
        System.out.println("Saving all movies for user ID "+ payload.getUserId()+"...");
        List<Movie> list = payload.getMovies();
        int userId = payload.getUserId();
        List<MovieRecord> savedMovies = new ArrayList<>();
        list.stream().forEach(movie -> {

            MovieRecord m = saveMovie(movie, userId);
            if (m != null) savedMovies.add(m);
        });
        System.out.println("Saving completed for user ID "+ payload.getUserId()+"...");
        return savedMovies;
    }

    private MovieRecord saveMovie(Movie movie, int userId) {
        if (getMovieByNameAndUser(movie.getName(), userId) == null) {
            List<String> genres = movie.getGenres();
            HttpEntity<List<String>> request = new HttpEntity<List<String>>(genres, new HttpHeaders());
            ResponseEntity<Integer[]> res = restTemplate.postForEntity("http://user/genres", request, Integer[].class);
            System.out.println("Genre IDs : ");
            System.out.println(res.getBody());
            MovieRecord movieRecord = new MovieRecord();
            movieRecord.setName(movie.getName());
            movieRecord.setImdb(movie.getImdb());
            movieRecord.setImg(movie.getImg());
            movieRecord.setLink(movie.getLink());
            movieRecord.setUserId(userId);
            MovieRecord mr = movieRepository.save(movieRecord);
            movie_genreService.saveAllGenres(mr.getId(), Arrays.asList(res.getBody()));
            return mr;
        }
        return null;
    }

    private MovieRecord getMovieByNameAndUser(String name, int userId) {
        List<MovieRecord> mrList = movieRepository.findAllByName(name);
        return mrList.stream().filter(m->m.getUserId()==userId).findFirst().orElse(null);
    }
}
