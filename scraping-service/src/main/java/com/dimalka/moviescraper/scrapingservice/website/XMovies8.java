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

public class XMovies8 {
    private static Registry registry = new Registry();

    public static List<Movie> getScrapedMovies(String url) throws IOException, CloneNotSupportedException {


        Document document = null;
        List<Movie> movieList = new ArrayList<>();
        Registry registry = new Registry();
        document = Jsoup.connect(url).get();
        List<String> linksForEachMovie = new ArrayList<>();

        Elements elements = document.getElementsByClass("poster_content-list__11G1q");
        elements.get(1).getElementsByTag("a").stream().forEach(a -> {
            linksForEachMovie.add("https://xmovies8.pw" + a.attr("href"));
        });
//        System.out.println(linksForEachMovie);
//        System.out.println(document.getElementsByClass("poster_content-list__11G1q"));
        return getMoviesFromArticles(linksForEachMovie);


    }

    private static List<Movie> getMoviesFromArticles(List<String> urls) {
//        Document document = null;
        List<Movie> movieList = new ArrayList<>();
        Registry registry = new Registry();
//        document = Jsoup.connect(url).get();
        urls.stream().forEach(url -> {
            try {
                Document document = Jsoup.connect(url).get();
                Movie movie = registry.getMovieInstance();
                Element e = document.getElementsByClass("watch_content-info__1w1R2").first();
                movie.setName(e.getElementsByClass("watch_top__3utMB").first().getElementsByTag("h1").first().ownText());
                List<String> genres = new ArrayList<>();
                movie.setImg(e.getElementsByClass("watch_poster__S6P9p").first().attr("src"));
                Elements metaDataElements = e.getElementsByClass("watch_meta-block__3hEih");
                metaDataElements.stream().forEach(ee -> {
                    Elements es = ee.getElementsByClass("watch_meta-item-genre__dBVfm");
                    if (es != null) {
                        es.stream().forEach(ess -> {
                            genres.add(ess.ownText());
                        });
                    }
//                    ee.getElementsByTag("span").first().ownText().equals("IMDB")
                    if (ee.getElementsByTag("span").size() > 0) {
                        String text = ee.getElementsByTag("span").first().ownText();
                        switch (text) {
                            case "IMDB":
                                movie.setImdb(ee.getElementsByTag("p").first().ownText() + " / 10");
                                break;
                            case "Release":

                                movie.setYear(Integer.parseInt(ee.getElementsByTag("a").first().ownText()));
                                break;
                        }
                    }

                });
                movie.setLink(url);
                movie.setGenres(genres);
                movieList.add(movie);
            } catch (IOException | CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
        movieList.stream().forEach(m -> {
            System.out.println(m);
        });
        return movieList;
    }

}
