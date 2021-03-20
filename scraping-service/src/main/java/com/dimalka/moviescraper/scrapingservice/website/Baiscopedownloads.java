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

public class Baiscopedownloads {
    private static String searchingElementClass = "single-article";
    private static Registry registry = new Registry();

    public static List<Movie> getScrapedMovies(String url) throws IOException, CloneNotSupportedException {

        Document document = null;
        List<Movie> movieList = new ArrayList<>();

        document = Jsoup.connect(url).get();

        Elements elements = document.getElementsByClass(searchingElementClass);
        List<Element> movieArticles = elements.subList(4, 9);
        List<String> movieLinks = new ArrayList<>();
        for (Element e : movieArticles
        ) {
            movieLinks.add(e.getElementsByTag("a").get(0).attr("href"));
        }
        movieList = getMovieFromArticle(movieLinks);

        return movieList;
    }

    private static List<Movie> getMovieFromArticle(List<String> urls) {
        List<Movie> list = new ArrayList<>();

        urls.stream().forEach(url->{
            Document movieArticle=null;
            Movie movie = null;
            try {
                movie = registry.getMovieInstance();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            List<String> genres = new ArrayList<>();
            try {
                movieArticle = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String title = movieArticle
                    .getElementsByClass("entry-title")
                    .first()
                    .ownText()
                    .split(String.valueOf(" \\("))[0];
            int year = Integer.parseInt(movieArticle
                    .getElementsByClass("entry-title")
                    .first()
                    .ownText()
                    .split(String.valueOf(" \\("))[1].split(String.valueOf("\\)"))[0]);
            String rating = "N/A";
            String image = movieArticle
                    .getElementsByClass("attachment-colormag-featured-image size-colormag-featured-image wp-post-image")
                    .get(0)
                    .attr("src");
            String link = url;

            for (Element e : movieArticle.getElementsByClass("cat-links").get(0).getElementsByTag("a")
            ) {
                genres.add(e.ownText());
            }
            movie.setGenres(genres);
            movie.setImdb(rating);
            movie.setName(title);
            movie.setLink(link);
            movie.setImg(image);
            movie.setYear(year);
            list.add(movie);
        });

        return list;
    }


}
