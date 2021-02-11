package com.dimalka.moviescraper.notificationservice.controller;


import com.dimalka.moviescraper.notificationservice.service.JavaMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Controller {
@Autowired
    JavaMailService javaMailService;
//@PostMapping(value = "/send")
//    public void send(@RequestBody List<Movie> movies, @RequestParam String emailAddress){
//
//    }
    @PostMapping("/sendmail")
    public void send(@RequestBody Map<String, Object> obj){


        javaMailService.sendEmail(obj.get("to").toString(), obj.get("shortMsg").toString(), obj.get("subject").toString());

    }
}
