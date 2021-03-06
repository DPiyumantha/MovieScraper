package com.dimalka.moviescraper.scrapingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
@EnableResourceServer
public class ScrapingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrapingServiceApplication.class, args);
    }
}
