package com.dimalka.moviescraper.discoveryservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServiceApplication.class, args);
    }

}
@RestController
class EurekaClientRestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/hello")
    public @ResponseBody
    String sayHello() {
        return "Hello!";
    }

    @RequestMapping("/clients/{applicationName}")
    public @ResponseBody
    String getClientsByApplicationName(@PathVariable String applicationName) {
        if(this.discoveryClient.getInstances(applicationName).size()==0){
            return "No instances";
        }
        return this.discoveryClient.getInstances(applicationName).get(0).getUri().toString();
    }
}
