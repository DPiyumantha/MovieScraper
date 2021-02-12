package com.dimalka.moviescrapercommons.model.notificationservice;


import com.dimalka.moviescrapercommons.model.repositoryservice.MovieRecord;
import com.dimalka.moviescrapercommons.model.scrapingservice.Movie;
import lombok.Data;

import java.util.List;

@Data
public class MailRequest {

    private String name;
    private String to;
    private String from;
    private String subject;
    private List<MovieRecord> allMovies;
    private List<MovieRecord> filteredMovies;

}
