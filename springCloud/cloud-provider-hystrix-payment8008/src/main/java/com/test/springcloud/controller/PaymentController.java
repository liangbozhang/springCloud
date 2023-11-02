package com.test.springcloud.controller;

import com.test.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/info/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Long id){
        return paymentService.paymentInfoOk(id);
    }

    @GetMapping("/info/time/out/{id}")
    public String paymentInfoTimeOut(@PathVariable("id") Long id){
        long s = System.currentTimeMillis();
        String str = paymentService.paymentInfoTimeOut(id);
        long end = System.currentTimeMillis();
        System.out.println(str + "{" + (end - s) + "}");
        return str;
    }

    //服务降级
    @GetMapping("/hystrix/downgrading/{id}")
    public String paymentHystrixDowngrading(@PathVariable("id") Long id){
        return paymentService.paymentHystrixDowngrading(id);
    }


    //服务熔断
    @GetMapping("/hystrix/fusing/{id}")
    public String paymentHystrixFusing(@PathVariable("id") Long id){
        return paymentService.paymentHystrixFusing(id);
    }
}
