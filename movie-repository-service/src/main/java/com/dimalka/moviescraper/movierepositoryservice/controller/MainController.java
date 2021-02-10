package com.dimalka.moviescraper.movierepositoryservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    public String greet(){
        return "Hi there";
    }
}
