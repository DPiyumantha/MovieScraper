package com.dimalka.moviescraper.scrapingservice.model;

import com.dimalka.moviescrapercommons.model.scrapingservice.Movie;

import java.util.ArrayList;

public class Registry {
com.dimalka.moviescrapercommons.model.scrapingservice.Movie movie;

    public Registry(){
        movie= new Movie("Movie", new ArrayList<>(), "URL", "Rating", "Image");
    }

    public Movie getMovieInstance() throws CloneNotSupportedException {
        return (Movie)(movie.clone());
    }
}
