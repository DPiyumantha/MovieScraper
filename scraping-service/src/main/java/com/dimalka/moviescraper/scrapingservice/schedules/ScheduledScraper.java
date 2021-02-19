package com.dimalka.moviescraper.scrapingservice.schedules;


import com.dimalka.moviescraper.scrapingservice.service.Scraper;
import com.dimalka.moviescrapercommons.model.repositoryservice.MovieRecord;
import com.dimalka.moviescrapercommons.model.scrapingservice.Movie;
import com.dimalka.moviescrapercommons.model.scrapingservice.MoviePayload;
import com.dimalka.moviescrapercommons.model.userservice.Genre;
import com.dimalka.moviescrapercommons.model.userservice.User;
import com.dimalka.moviescrapercommons.model.userservice.WebSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.io.IOException;
import java.util.*;

@Component
public class ScheduledScraper {

//    @Bean
//    @LoadBalanced
//    RestTemplate getRestTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }



    @Autowired
    RestTemplate restTemplate;
    @Autowired
    Scraper scraper;

    //    @Scheduled(cron = "0 0 12 * * ?", zone = "GMT+5:30")
    @Scheduled(fixedRate = 10000)
    public void scheduledScraping() {
        System.out.println("Executing scheduled scraping...");
        HttpHeaders headers = new HttpHeaders();
        HttpHeaders headersForResource = new HttpHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        MultiValueMap<String, String> mapforservice = new LinkedMultiValueMap<>();

        String plainCreds = "scraping-service:scrapingpin";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);


        map.add("grant_type", "password");
        map.add("username", "admin");
        map.add("password", "admin123");

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + base64Creds);

        HttpEntity<MultiValueMap<String, String>> oauth2request = new HttpEntity<>(map, headers);
        HttpEntity<MultiValueMap<String, String>> servicerequest = new HttpEntity<>(mapforservice, headersForResource);

        ResponseEntity<String> response = restTemplate.postForEntity("http://authorization-server/oauth/token", oauth2request, String.class);
        String authToken = response.getBody().split("\"")[3];
        System.out.println("Auth token "+authToken);

        headersForResource.add("Authorization", "Bearer " + authToken);
        HttpEntity serviceRequest = new HttpEntity(headersForResource);

        ResponseEntity<User[]> users = restTemplate.exchange("http://user/user-api/users",HttpMethod.GET, serviceRequest, User[].class);

        Arrays.asList(users.getBody()).stream().forEach(user -> {
            int userId = user.getId();
            List<Movie> movies = new ArrayList<>();
            List<Genre> genres = user.getGenres();
            List<WebSite> websites = user.getWebSites();
            websites.stream().forEach(website -> {
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

            System.out.println(payload);

        });

    }
}
