package com.dimalka.moviescraper.movierepositoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.dimalka.moviescrapercommons.model.repositoryservice")
public class MovieRepositoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieRepositoryServiceApplication.class, args);
    }

}
