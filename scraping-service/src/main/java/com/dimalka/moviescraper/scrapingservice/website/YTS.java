package com.dimalka.moviescraper.scrapingservice.website;

import com.dimalka.moviescraper.scrapingservice.model.Registry;
import com.dimalka.moviescrapercommons.model.scrapingservice.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YTS {
    private static String searchingElementClass = "browse-movie-wrap";
    public static List<Movie> getScrapedMovies(String url) throws IOException, CloneNotSupportedException {

        Document document = null;
        List<Movie> movieList = new ArrayList<>();
        Registry registry = new Registry();
        document = Jsoup.connect(url).get();

        Elements elements = document.getElementsByClass(searchingElementClass);
//        elements = elements.

        for (Element e : elements
        ) {
            Movie movie = registry.getMovieInstance();
            Element figure = e.getElementsByTag("figure").get(0);
            Elements h4s = figure.getElementsByTag("h4");
            List<String> genres = new ArrayList<>();
            String imdb;

            if (h4s.size() > 0) {
                imdb = h4s.get(0).ownText();
            } else {
                imdb = "N/A";
            }
            if (h4s.size() == 2) {
                genres.add(h4s.get(1).ownText());

            } else if(h4s.size()>2){
                genres.add(h4s.get(1).ownText());
                genres.add(h4s.get(2).ownText());
            }else {
                genres.add("N/A");
            }
            String nameA = e.getElementsByClass("browse-movie-bottom").get(0).getAllElements().get(1).ownText();
            String urlA = e.getElementsByClass("browse-movie-bottom").get(0).getAllElements().get(1).attr("href");
            movie.setName(nameA);
            movie.setLink(urlA);
            movie.setImdb(imdb);
            movie.setGenres(genres);
            movieList.add(movie);
        }
        return movieList;
    }


}
