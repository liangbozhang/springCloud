package com.test.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.test.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("consumer")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/ok/{id}")
    public String ok(@PathVariable("id") Long id){
        return orderService.paymentInfoOk(id);
    }

    @GetMapping("/timeOut/{id}")
    public String timeOut(@PathVariable("id") Long id){
        return orderService.paymentInfoTimeOut(id);
    }

    //服务降级
    @GetMapping("/out/{id}")
    @HystrixCommand
    public String timeOut1(@PathVariable("id") Long id){
        return orderService.paymentInfoTimeOut1(id);
    }


}
