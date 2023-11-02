package com.test.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("consul")
    public String get(){
        return "springCloud with consul： " + port + "\t  " + UUID.randomUUID().toString();
    }
}
