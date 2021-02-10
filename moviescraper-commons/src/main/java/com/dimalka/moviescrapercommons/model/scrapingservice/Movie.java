package com.dimalka.moviescrapercommons.model.scrapingservice;

import java.util.List;

public class Movie implements Cloneable {
    String name;
    List<String> genres;
    String link;
    String imdb;
    String img;

    public Movie() {
    }

    public Movie(String name, List<String> genres, String link, String imdb, String img) {
        this.name = name;
        this.genres = genres;
        this.link = link;
        this.imdb = imdb;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();

    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", genres=" + genres +
                ", link='" + link + '\'' +
                ", imdb='" + imdb + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
