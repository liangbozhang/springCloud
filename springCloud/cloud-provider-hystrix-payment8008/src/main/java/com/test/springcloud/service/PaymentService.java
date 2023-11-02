package com.test.springcloud.service;

public interface PaymentService {

    String paymentInfoOk(Long id);

    String paymentInfoTimeOut(Long id);

    //服务降级
    String paymentHystrixDowngrading(Long id);

    //服务熔断
    String paymentHystrixFusing(Long id);

}
