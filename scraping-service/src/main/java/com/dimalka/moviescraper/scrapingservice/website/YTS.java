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

public class YTS {
    private static String searchingElementClass = "browse-movie-wrap";

    public static List<Movie> getScrapedMovies(String url) throws IOException, CloneNotSupportedException {

        Document document = null;
        List<Movie> movieList = new ArrayList<>();
        Registry registry = new Registry();
        document = Jsoup.connect(url).get();
        List<String> movieLinks = new ArrayList<>();
        Elements elements = document.getElementsByClass(searchingElementClass);
        elements.stream().forEach(e -> {
            String urlA = e.getElementsByClass("browse-movie-bottom").get(0).getAllElements().get(1).attr("href");
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
                Element e = document.getElementById("movie-info");
                movie.setName(e.getElementsByTag("h1").first().ownText());
                movie.setYear(Integer.parseInt(e.getElementsByTag("h2").first().ownText()));
                Arrays.stream(e.getElementsByTag("h2").get(1).ownText().split("/")).forEach(word -> {
                    genres.add(word.trim());
                });
                movie.setGenres(genres);
                e.getElementsByClass("rating-row").stream().forEach(row -> {
                    if (row.getElementsByTag("a").first()!=null &&row.getElementsByTag("a").first().attr("href").contains("imdb")) {
                        row.getElementsByTag("span").stream().forEach(span -> {
                            if (span.attr("itemprop").equals("ratingValue")) {
                                movie.setImdb(span.ownText()+" / 10");
                            }
                        });
                    }
                });
                String imgUrl = document.getElementById("movie-poster").getElementsByTag("img").first().attr("src");
                movie.setImg(imgUrl);
                movie.setLink(url);
                movieList.add(movie);
            } catch (IOException | CloneNotSupportedException e) {
                e.printStackTrace();
            }

        });
        return movieList;
    }


}



/*
for (Element e : elements
        ) {
            Movie movie = registry.getMovieInstance();
            Element figure = e.getElementsByTag("figure").get(0);
            Elements h4s = figure.getElementsByTag("h4");
            List<String> genres = new ArrayList<>();
            String imdb="N/A";
            String imgA = url + e.getElementsByTag("img").first().attr("src");

            if(h4s.size()==0){
                genres.add("N/A");
            }else if(h4s.size()==1){
                if(h4s.get(0).ownText().contains("/")){
                    imdb=h4s.get(0).ownText();
                    genres.add("N/A");
                }else{
                    genres.add(h4s.get(0).ownText());
                }
            }else if(h4s.size()>1){
                if(h4s.get(0).ownText().contains("/")){
                    imdb=h4s.get(0).ownText();
                }else{
                    h4s.forEach(h4->genres.add(h4.ownText()));
                }
            }
            String nameA = e.getElementsByClass("browse-movie-bottom").get(0).getAllElements().get(1).ownText();
            String urlA = e.getElementsByClass("browse-movie-bottom").get(0).getAllElements().get(1).attr("href");
            int yearA = Integer.parseInt(e.getElementsByClass("browse-movie-bottom").first().getElementsByClass("browse-movie-year").first().ownText().split(" ")[0]);
            if (e.getElementsByClass("browse-movie-bottom").first().getElementsByClass("browse-movie-year").first().ownText().split(" ").length == 1) {
                movie.setName(nameA);
                movie.setLink(urlA);
                movie.setImdb(imdb);
                movie.setGenres(genres);
                movie.setYear(yearA);
                movie.setImg(imgA);
                System.out.println(movie);
                movieList.add(movie);
            }

        }
 */
