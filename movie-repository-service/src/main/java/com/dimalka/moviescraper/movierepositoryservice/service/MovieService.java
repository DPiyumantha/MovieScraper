package com.dimalka.moviescraper.movierepositoryservice.service;

import com.dimalka.moviescraper.movierepositoryservice.repository.MovieRepository;
import com.dimalka.moviescrapercommons.model.notificationservice.MailRequest;
import com.dimalka.moviescrapercommons.model.notificationservice.MailResponse;
import com.dimalka.moviescrapercommons.model.scrapingservice.Movie;
import com.dimalka.moviescrapercommons.model.repositoryservice.MovieRecord;
import com.dimalka.moviescrapercommons.model.scrapingservice.MoviePayload;
import com.dimalka.moviescrapercommons.model.userservice.Genre;
import com.dimalka.moviescrapercommons.model.userservice.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class MovieService {
    @Bean
    @LoadBalanced
    RestTemplate getRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    ;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MovieRepository movieRepository;


    public List<MovieRecord> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<MovieRecord> saveAllMovies(MoviePayload payload) {

        List<Movie> list = payload.getMovies();
        int userId = payload.getUserId();
        System.out.println("Saving all movies for user ID "+ userId+"...");
        List<MovieRecord> savedMovies = new ArrayList<>();
        list.stream().forEach(movie -> {

            MovieRecord m = saveMovie(movie, userId);
            if (m != null) savedMovies.add(m);
        });
        System.out.println("Saving completed for user ID "+ userId+"...");
        ResponseEntity<User> userFromUserServiceRes =
                restTemplate.exchange("http://user/user-api/users/"+userId, HttpMethod.GET, contactUserService(), User.class);
        User userFromUserService = userFromUserServiceRes.getBody();
        System.out.println("Saved movies : "+savedMovies.size());
        List<Integer> userPreferredGenresIds = new ArrayList<>();
        userFromUserService.getGenres().stream().forEach(genre -> userPreferredGenresIds.add(genre.getId()));
        List<MovieRecord> filteredMovies = new ArrayList<>();
        savedMovies.stream().forEach(movieRecord -> {
            AtomicBoolean matched = new AtomicBoolean(false);
            List<Integer> genreIdsOfSavedMovie = new ArrayList<>();
            movieRecord.getGenres().stream().forEach(genre -> genreIdsOfSavedMovie.add(genre.getId()));
            genreIdsOfSavedMovie.stream().forEach(gid->{
                if(userPreferredGenresIds.contains(gid)){
                    matched.set(true);}
            });
            if(matched.get()){filteredMovies.add(movieRecord);}

        });
        if(savedMovies.size()>0){
            MailRequest mailRequest = new MailRequest();
            mailRequest.setName(userFromUserService.getFirstName());
            mailRequest.setTo(userFromUserService.getUserEmail());
            mailRequest.setFrom("dimalka.piumantha@gmail.com");
            mailRequest.setSubject("[MovieScraper] We found new movies for you!");
            mailRequest.setAllMovies(savedMovies);
            mailRequest.setFilteredMovies(filteredMovies);
            System.out.println("Sending mail request...");
            restTemplate.postForEntity("http://notification/sendmail", mailRequest, MailResponse.class);
        }
        return savedMovies;
    }

    private MovieRecord saveMovie(Movie movie, int userId) {
        if (getMovieByNameAndUser(movie.getName(), userId) == null) {
            List<String> genres = movie.getGenres();
            HttpEntity<List<String>> request = new HttpEntity<List<String>>(genres, new HttpHeaders());

            ResponseEntity<Genre[]> genreObjs =
                restTemplate.exchange("http://user/genre-api/genreobjs", HttpMethod.POST, contactUserService(genres), Genre[].class);
            List<com.dimalka.moviescrapercommons.model.repositoryservice.Genre> listOfGenres = new ArrayList<>();
            Arrays.stream(Objects.requireNonNull(genreObjs.getBody())).forEach(g->{
                com.dimalka.moviescrapercommons.model.repositoryservice.Genre gs =
                        new com.dimalka.moviescrapercommons.model.repositoryservice.Genre();
                gs.setId(g.getId());
                gs.setName(g.getName());
                listOfGenres.add(gs);

            });
            MovieRecord movieRecord = new MovieRecord();
            movieRecord.setName(movie.getName());
            movieRecord.setImdb(movie.getImdb());
            movieRecord.setImg(movie.getImg());
            movieRecord.setLink(movie.getLink());
            movieRecord.setUserId(userId);
            movieRecord.setGenres(listOfGenres);
            movieRecord.setYear(movie.getYear());
            return movieRepository.save(movieRecord);
        }
        return null;
    }

    private MovieRecord getMovieByNameAndUser(String name, int userId) {
        List<MovieRecord> mrList = movieRepository.findAllByName(name);
        return mrList.stream().filter(m->m.getUserId()==userId).findFirst().orElse(null);
    }

    private HttpEntity contactUserService(){
        HttpHeaders headers = new HttpHeaders();
        HttpHeaders headersForResource = new HttpHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        String plainCreds = "scraping-service:scrapingpin";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);


        map.add("grant_type", "password");
        map.add("username", "admin");
        map.add("password", "admin123");

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + base64Creds);

        HttpEntity<MultiValueMap<String, String>> oauth2request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity("http://authorization-server/oauth/token", oauth2request, String.class);
        String authToken = response.getBody().split("\"")[3];
        System.out.println("Auth token "+authToken);

        headersForResource.add("Authorization", "Bearer " + authToken);
        HttpEntity serviceRequest = new HttpEntity(headersForResource);

        return serviceRequest;

    }

    private HttpEntity contactUserService(List<String> body){
        HttpHeaders headers = new HttpHeaders();
        HttpHeaders headersForResource = new HttpHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        String plainCreds = "scraping-service:scrapingpin";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);


        map.add("grant_type", "password");
        map.add("username", "admin");
        map.add("password", "admin123");

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + base64Creds);

        HttpEntity<MultiValueMap<String, String>> oauth2request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity("http://authorization-server/oauth/token", oauth2request, String.class);
        String authToken = response.getBody().split("\"")[3];
        System.out.println("Auth token "+authToken);

        headersForResource.add("Authorization", "Bearer " + authToken);
        HttpEntity serviceRequest = new HttpEntity(body,headersForResource);

        return serviceRequest;

    }

    public List<MovieRecord> getAllMoviesByUserId(int id) {
        return movieRepository.findAllByUserId(id);
    }
}
