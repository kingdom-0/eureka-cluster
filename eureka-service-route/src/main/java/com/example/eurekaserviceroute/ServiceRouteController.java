package com.example.eurekaserviceroute;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Edison Wang
 * @version 1.0
 * @description TODO
 * @date 2023/4/27 16:51
 */

@Slf4j
@EnableFeignClients
@RestController
public class ServiceRouteController {


    @GetMapping("/data")
    public String getData(){
        log.info("get data method was called.");
        return "Message from service route.";
    }
}
