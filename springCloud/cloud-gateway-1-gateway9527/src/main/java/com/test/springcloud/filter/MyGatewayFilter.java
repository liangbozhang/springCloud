package com.test.springcloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
public class MyGatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("*********************** come in MyGatewayFilter: " + new Date());
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if (username == null){
            System.out.println("**********用户名为null， 非法用户 ！！！");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            System.out.println("*********************** come out MyGatewayFilter: " + new Date());
            return exchange.getResponse().setComplete();
        }
        System.out.println("*********************** come out MyGatewayFilter: " + new Date());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
