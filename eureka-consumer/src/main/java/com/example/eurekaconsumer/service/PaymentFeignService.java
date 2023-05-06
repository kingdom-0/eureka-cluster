package com.example.eurekaconsumer.service;

import com.power.common.model.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Edison Wang
 * @version 1.0
 * @description TODO
 * @date 2023/5/5 18:08
 */

@Component
@FeignClient(value = "eurekauser")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);
}
