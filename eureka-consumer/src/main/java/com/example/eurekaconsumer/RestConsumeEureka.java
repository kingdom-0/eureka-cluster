package com.example.eurekaconsumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Edison Wang
 * @version 1.0
 * @description TODO
 * @date 2023/4/26 11:30
 */

@RestController
public class RestConsumeEureka {

    @Value("${server.port}")
    private String port;

    private RestTemplate rest;

    public RestConsumeEureka(@LoadBalanced RestTemplate rest){
        this.rest = rest;
    }

    @GetMapping(path = "/getData")
    public String getData(){
        System.out.println("消费Erueka中注册的服务...");
        String ret = rest.getForObject("http://EUREKAUSER/rest/getData",String.class);
        System.out.println(ret);
        return ret;
    }
}
