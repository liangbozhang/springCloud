package com.test.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("consumer")
public class OrderController {

    @Value("${service-url.nacos-user-service}")
    private String serviceUrl;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("port")
    public String get() {
        return restTemplate.getForObject(serviceUrl + "/payment/port", String.class);
    }
}
