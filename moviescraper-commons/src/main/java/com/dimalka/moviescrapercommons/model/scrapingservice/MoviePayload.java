package com.dimalka.moviescrapercommons.model.scrapingservice;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
@Getter
@Setter
public class MoviePayload {
    int userId;
    List<Movie> movies;
}
