package com.dimalka.moviescraper.userservice.controller;

import com.dimalka.moviescraper.userservice.service.WebsiteService;
import com.dimalka.moviescrapercommons.model.userservice.WebSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/website-api")
public class WebsiteController {
    @Autowired
    WebsiteService websiteService;

    @PostMapping("/websites")
    public WebSite save(@RequestBody WebSite webSite) {
        return websiteService.saveWebsite(webSite);
    }

    @GetMapping("/websites")
    public List<WebSite> getAllWebsites() {
        return websiteService.getAllWebsites();
    }

}
