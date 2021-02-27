package com.dimalka.moviescraper.notificationservice.controller;


import com.dimalka.moviescraper.notificationservice.service.JavaMailService;
import com.dimalka.moviescrapercommons.model.notificationservice.MailRequest;
import com.dimalka.moviescrapercommons.model.notificationservice.MailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailService javaMailService;

    @PostMapping("/sendmail")
    public MailResponse send(@RequestBody MailRequest mailRequest) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", mailRequest.getName());
        model.put("allMovies", mailRequest.getAllMovies());
        model.put("filteredMovies", mailRequest.getFilteredMovies());
        return javaMailService.sendEmail(mailRequest, model);

    }
}
