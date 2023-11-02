package com.test.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 编码配置
 *
 */
@Configuration
public class GatewayConfig {

    /**
     * 配置一个id为 path_route_test 的路由规则
     * 当访问地址：http://localhost:9527/guonei 时会自动转发到地址：http://news.baidu.con/guonei
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRoute(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();

        routes.route("path_route_test", r -> r.path("/guonei").uri("https://news.cctv.com/?spm=C88965.P72990804435.E2XVQsMhlk44.3")).build();
        return routes.build();
    }

    @Bean
    public RouteLocator customRoute1(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();

        routes.route("path_route_test1", r -> r.path("/guoji").uri("https://v.cctv.com/?spm=C94212.P4YnMod9m2uD.E2XVQsMhlk44.5")).build();
        return routes.build();
    }

}
