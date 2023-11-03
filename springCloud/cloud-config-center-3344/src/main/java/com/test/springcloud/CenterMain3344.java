package com.test.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CenterMain3344 {

    public static void main(String[] args) {
        SpringApplication.run(CenterMain3344.class, args);
    }
}
