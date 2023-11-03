package com.test.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //动态刷新之手动刷新 需要发送post请求，命令行（cmd）发送post请求：curl -X POST "http://localhost:3355/actuator/refresh"
@RequestMapping("client")
public class ClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("config")
    public String get(){
        return configInfo;
    }

}
