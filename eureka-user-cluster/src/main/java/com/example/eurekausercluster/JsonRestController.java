package com.example.eurekausercluster;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Edison Wang
 * @version 1.0
 * @description TODO
 * @date 2023/4/26 14:58
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/rest")
public class JsonRestController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/getData")
    public String getData(){
        String data = "{\"eurekauser2服务提供者端口-----\":"+port+", \"id\": 2, \"title\": \"json title标题\", \"arrlist\": [\"JAVA\", \"JavaScript\", \"PHP\"]}";
        return data;
    }
}
