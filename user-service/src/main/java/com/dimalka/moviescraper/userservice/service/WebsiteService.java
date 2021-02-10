package com.dimalka.moviescraper.userservice.service;

import com.dimalka.moviescraper.userservice.repository.GenreRepository;
import com.dimalka.moviescraper.userservice.repository.WebsiteRepository;
import com.dimalka.moviescrapercommons.model.userservice.Genre;
import com.dimalka.moviescrapercommons.model.userservice.WebSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WebsiteService {

    @Autowired
    WebsiteRepository websiteRepository;

    public WebSite saveWebsite(WebSite webSite){
        return websiteRepository.save(webSite);
    }

    public List<WebSite> getAllWebsites(){
        return websiteRepository.findAll();
    }

    public WebSite getWebsiteById(int id){
        return websiteRepository.findById(id).orElse(null);
    }
}
