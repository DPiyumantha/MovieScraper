package com.dimalka.projectui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfigurer {

    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
