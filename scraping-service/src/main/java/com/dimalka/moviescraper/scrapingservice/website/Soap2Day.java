package com.dimalka.moviescraper.scrapingservice.website;

import com.dimalka.moviescraper.scrapingservice.model.Registry;
import com.dimalka.moviescrapercommons.model.scrapingservice.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Soap2Day {


    private static String searchingElementClass = "shortstory-in";

    public static List<Movie> getScrapedMovies(String url) throws IOException, CloneNotSupportedException {

        Document document = null;
        List<Movie> movieList = new ArrayList<>();
        Registry registry = new Registry();
        document = Jsoup.connect(url).get();
        List<String> movieLinks = new ArrayList<>();
        Elements elements = document.getElementsByClass(searchingElementClass);
        elements.stream().forEach(e -> {
            String urlA = e.getElementsByTag("a").first().attr("href");
            movieLinks.add(urlA);
        });
        System.out.println(movieLinks);
        return getMoviesFromArticles(movieLinks);
    }

    private static List<Movie> getMoviesFromArticles(List<String> urls) {
        List<Movie> movieList = new ArrayList<>();
        Registry registry = new Registry();

        urls.stream().forEach(url -> {
            try {
                Document document = Jsoup.connect(url).get();
                Movie movie = registry.getMovieInstance();

                List<String> genres = new ArrayList<>();

                Elements fInfoBlocks = document.getElementsByClass("finfo-block");

                fInfoBlocks.stream().forEach(fInfo -> {
                    switch (fInfo.getElementsByClass("finfo-title").first().ownText()) {
                        case "Title:":
                            movie.setName(fInfo.getElementsByClass("finfo-text").first().getElementsByTag("a").first().ownText());
                            break;
                        case "Rating:":
                            movie.setImdb("N/A");
                            break;
                        case "Year:":
                            movie.setYear(Integer.parseInt(fInfo.getElementsByTag("a").first().ownText()));
                            break;
                        case "Genre:":
                            fInfo.getElementsByTag("a").stream().forEach(a -> {
                                genres.add(genreMapper(a.ownText()));
                            });
                            break;


                    }
                });
                movie.setImg("https://www.ssoap2day.to/" + document.getElementsByClass("fstory-poster").first().getElementsByTag("img").first().attr("src"));
                movie.setLink(url);
                movieList.add(movie);

            } catch (IOException | CloneNotSupportedException e) {
                e.printStackTrace();
            }

        });
        return movieList;
    }

    private static String genreMapper(String genre) {
        switch (genre) {
            case "Science Fiction":
                return "Sci-Fi";
            default:
                return firstCharCapital(genre);
        }
    }

    private static String firstCharCapital(String text){
        return text.toUpperCase().toCharArray()[0]+ text.substring(1);
    }


}