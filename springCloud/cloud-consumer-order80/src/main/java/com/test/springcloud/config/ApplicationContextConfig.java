package com.test.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    //@LoadBalanced   //注解 @LoadBalanced 赋予 RestTemplate 负载均衡能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
