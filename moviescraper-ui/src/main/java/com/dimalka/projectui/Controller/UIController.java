package com.dimalka.projectui.Controller;

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

    @RequestMapping("/secure")
    public String loadSecureUI(){
        return "secure";
    }

    @RequestMapping("/projects")
    public String loadProjects(Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessToken.getAccessToken());

        HttpEntity<Project> projectHttpEntity = new HttpEntity<>(httpHeaders);
        try {
            ResponseEntity<Project[]> responseEntity = restTemplate.exchange("http://localhost:3710/project", HttpMethod.GET, projectHttpEntity, Project[].class);
            model.addAttribute("projects", responseEntity.getBody());
        }catch (HttpStatusCodeException e){
            ResponseEntity responseEntity = ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders()).body(e.getResponseBodyAsString());
            model.addAttribute("error", responseEntity);
        }

        return "secure";
    }
}
