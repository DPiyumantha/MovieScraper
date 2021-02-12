package com.dimalka.moviescraper.scrapingservice.schedules;


import com.dimalka.moviescraper.scrapingservice.service.Scraper;
import com.dimalka.moviescrapercommons.model.notificationservice.MailRequest;
import com.dimalka.moviescrapercommons.model.notificationservice.MailResponse;
import com.dimalka.moviescrapercommons.model.repositoryservice.MovieRecord;
import com.dimalka.moviescrapercommons.model.scrapingservice.Movie;
import com.dimalka.moviescrapercommons.model.scrapingservice.MoviePayload;
import com.dimalka.moviescrapercommons.model.userservice.Genre;
import com.dimalka.moviescrapercommons.model.userservice.User;
import com.dimalka.moviescrapercommons.model.userservice.WebSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.io.IOException;
import java.util.*;

@Component
public class ScheduledScraper {

    @Bean
    @LoadBalanced
    RestTemplate getRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    ;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    Scraper scraper;

    //    @Scheduled(cron = "0 0 12 * * ?", zone = "GMT+5:30")
    @Scheduled(fixedRate = 10000)
    public void scheduledScraping() {
        System.out.println("Executing scheduled scraping...");
        ResponseEntity<User[]> users = restTemplate.getForEntity("http://user/users", User[].class);

        Arrays.asList(users.getBody()).stream().forEach(user -> {
            int userId = user.getId();
            List<Movie> movies = new ArrayList<>();
            ResponseEntity<Genre[]> genres = restTemplate.getForEntity("http://user/user-genre/" + user.getId(), Genre[].class);
            ResponseEntity<WebSite[]> websites = restTemplate.getForEntity("http://user/user-website/" + user.getId(), WebSite[].class);
            Arrays.asList(websites.getBody()).stream().forEach(website -> {
                try {
                    List<Movie> movieList = scraper.getAllMovies(website.getUrl());
                    movies.addAll(movieList);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            });

            MoviePayload payload = new MoviePayload();

            payload.setMovies(movies);
            payload.setUserId(userId);

            System.out.println("Sending payload to movie-repository...");
            restTemplate.postForEntity("http://movie-repository/movies", payload, MovieRecord[].class);


        });

    }
}
