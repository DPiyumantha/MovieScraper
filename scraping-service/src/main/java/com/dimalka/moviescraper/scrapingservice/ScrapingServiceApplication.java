package com.dimalka.moviescraper.scrapingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ScrapingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrapingServiceApplication.class, args);
    }

}
