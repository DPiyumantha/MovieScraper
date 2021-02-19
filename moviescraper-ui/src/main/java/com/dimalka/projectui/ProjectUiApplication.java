package com.dimalka.projectui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication

public class ProjectUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectUiApplication.class, args);
    }

}
