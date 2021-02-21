package com.dimalka.projectui.Controller;

import com.dimalka.moviescrapercommons.model.repositoryservice.MovieRecord;
import com.dimalka.moviescrapercommons.model.userservice.User;
import com.dimalka.projectui.Model.Project;
import com.dimalka.projectui.config.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@EnableOAuth2Sso
public class UIController extends WebSecurityConfigurerAdapter {
    @Autowired
    RestTemplate restTemplate;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated();
    }

    @RequestMapping("/")
    public String loadUI(){
        return "home";
    }

    @RequestMapping("/logout")
    public String logout(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.logout();
        return "home";
    }

    @RequestMapping("/secure")
    public String loadSecureUI(){
        return "secure";
    }

    @RequestMapping("/all-movies")
    public String loadProjects(Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessToken.getAccessToken());
HttpEntity<User> userHttpEntity =new HttpEntity<>(httpHeaders);
        HttpEntity<MovieRecord> movieHttpEntity = new HttpEntity<>(httpHeaders);
        try {
            ResponseEntity<User> userresponseEntity = restTemplate.exchange("http://localhost:8990/user/user-api/users/user", HttpMethod.GET, userHttpEntity, User.class);
            ResponseEntity<MovieRecord[]> responseEntity = restTemplate.exchange("http://localhost:8990/movie-repository/movies/"+userresponseEntity.getBody().getId(), HttpMethod.GET, movieHttpEntity, MovieRecord[].class);
            model.addAttribute("allMovies", responseEntity.getBody());
        }catch (HttpStatusCodeException e){
            ResponseEntity responseEntity = ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders()).body(e.getResponseBodyAsString());
            model.addAttribute("error", responseEntity);
        }

        return "all-movies";
    }
}
