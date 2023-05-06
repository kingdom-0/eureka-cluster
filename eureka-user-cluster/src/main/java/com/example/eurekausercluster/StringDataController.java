package com.example.eurekausercluster;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Edison Wang
 * @version 1.0
 * @description TODO
 * @date 2023/4/27 16:23
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/data")
public class StringDataController {
    @GetMapping("/get")
    public String getjData(){
        String data = "data from eureka user cluster";
        return data;
    }
    @GetMapping("/exception")
    public String getException() throws Exception {
        System.out.println("getException method in eureka-user was called.");
        throw new Exception("mock exception.");
    }
}
