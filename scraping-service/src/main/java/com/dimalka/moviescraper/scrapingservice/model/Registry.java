package com.dimalka.moviescraper.scrapingservice.model;

import java.util.ArrayList;

public class Registry {

    private Movie movie;

    public Registry(){
        movie= new Movie("Movie", new ArrayList<>(), "URL", "Rating", "Image");
    }

    public Movie getMovieInstance() throws CloneNotSupportedException {
        return (Movie)(movie.clone());
    }
}
