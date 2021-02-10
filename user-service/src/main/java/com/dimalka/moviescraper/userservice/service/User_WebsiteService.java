package com.dimalka.moviescraper.userservice.service;

import com.dimalka.moviescraper.userservice.repository.User_WebsiteRepository;
import com.dimalka.moviescrapercommons.model.userservice.User_Website;
import com.dimalka.moviescrapercommons.model.userservice.WebSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class User_WebsiteService {

    @Autowired
    User_WebsiteRepository user_websiteRepository;
    @Autowired
    WebsiteService websiteService;

    public User_Website saveUserWebsite(User_Website user_website) {
        return user_websiteRepository.save(user_website);
    }

    public List<User_Website> getAllUser_Website() {
        return user_websiteRepository.findAll();
    }

    public List<WebSite> getWebsitesByUserId(int id) {
        List<WebSite> list = new ArrayList<>();
        List<Integer> websiteIds = user_websiteRepository.findByUserId(id);
        websiteIds.stream().forEach(item -> {
            list.add(websiteService.getWebsiteById(item));
        });
        return list;
    }
}
