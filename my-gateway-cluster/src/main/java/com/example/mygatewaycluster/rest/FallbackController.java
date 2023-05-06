package com.example.mygatewaycluster.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Edison Wang
 * @version 1.0
 * @description TODO
 * @date 2023/5/4 17:37
 */

@Controller
public class FallbackController {

    @RequestMapping("/fallback")
    @ResponseBody
    public String getData(){
        return "Service is not available now.(from my gateway cluster)";
    }
}
