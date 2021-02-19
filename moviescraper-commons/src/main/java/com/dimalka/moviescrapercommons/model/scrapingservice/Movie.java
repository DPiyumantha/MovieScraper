package com.dimalka.moviescrapercommons.model.scrapingservice;

import com.dimalka.moviescrapercommons.model.userservice.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.ManyToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movie implements Cloneable {
    String name;
    String link;
    String imdb;
    String img;
    List<String> genres;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();

    }

}
