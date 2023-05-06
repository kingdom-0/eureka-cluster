package com.example.mygateway.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Edison Wang
 * @version 1.0
 * @description TODO
 * @date 2023/4/27 17:59
 */

@Controller
public class FallbackController {

    @RequestMapping("/fallback")
    @ResponseBody
    public String fallback(){
        return "Service is not available now(from my gateway).";
    }
}
