package com.dimalka.moviescraper.notificationservice.controller;


import com.dimalka.moviescraper.model.scrapingservice.Movie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

@PostMapping(value = "/send")
    public void send(@RequestBody List<Movie> movies, @RequestParam String emailAddress){

    }
}
