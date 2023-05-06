package com.example.eurekaserviceroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaServiceRouteApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceRouteApplication.class, args);
    }


}
