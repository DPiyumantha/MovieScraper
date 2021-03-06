package com.dimalka.moviescraper.scrapingservice.service;

import com.dimalka.moviescraper.scrapingservice.website.Baiscopedownloads;
import com.dimalka.moviescraper.scrapingservice.website.Soap2Day;
import com.dimalka.moviescraper.scrapingservice.website.XMovies8;
import com.dimalka.moviescraper.scrapingservice.website.YTS;
import com.dimalka.moviescrapercommons.model.scrapingservice.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Scraper {

    public List<Movie> getAllMovies(String url) throws IOException, CloneNotSupportedException {

        switch (url) {
            case "https://yts.ag/browse-movies":
            case "https://yts.mx/browse-movies":

                return YTS.getScrapedMovies(url);
            case "https://baiscopedownloads.co":
                return Baiscopedownloads.getScrapedMovies(url);
            case "https://xmovies8.pw/xmovies8":
                return XMovies8.getScrapedMovies(url);
            case "https://www.ssoap2day.to/lastnews":
                return Soap2Day.getScrapedMovies(url);
            default:
                System.out.println("Unsupported website");
                return  new ArrayList<>();

        }
    }
}
