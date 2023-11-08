package com.test.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("port")
    public String port(){
        return "ServerPort：" + serverPort;
    }
}
