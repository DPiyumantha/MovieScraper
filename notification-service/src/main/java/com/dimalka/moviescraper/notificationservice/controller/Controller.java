package com.dimalka.moviescraper.notificationservice.controller;


import com.dimalka.moviescraper.notificationservice.service.JavaMailService;
import com.dimalka.moviescrapercommons.model.notificationservice.MailRequest;
import com.dimalka.moviescrapercommons.model.notificationservice.MailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {
    @Autowired
    private JavaMailService javaMailService;

    //@PostMapping(value = "/send")
//    public void send(@RequestBody List<Movie> movies, @RequestParam String emailAddress){
//
//    }
    @PostMapping("/sendmail")
    public MailResponse send(@RequestBody MailRequest mailRequest) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", mailRequest.getName());
//        model.put("Location", "Sri lanka");
        model.put("allMovies", mailRequest.getAllMovies());
        model.put("filteredMovies", mailRequest.getFilteredMovies());
        return javaMailService.sendEmail(mailRequest, model);

//        javaMailService.sendEmail(obj.get("to").toString(), obj.get("shortMsg").toString(), obj.get("subject").toString());

    }
}
