package com.dimalka.moviescraper.userservice.service;

import org.springframework.stereotype.Service;

@Service
public class User_WebsiteService {
    /*

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
        List<User_Website> userwebsites = user_websiteRepository.findAllByUserId(id);
        List<Integer> websiteIds = new ArrayList<>();
        userwebsites.stream().forEach(i->{
            websiteIds.add(i.getWebsiteId());
        });
        websiteIds.stream().forEach(item -> {
            list.add(websiteService.getWebsiteById(item));
        });
        return list;
    }

     */

}

