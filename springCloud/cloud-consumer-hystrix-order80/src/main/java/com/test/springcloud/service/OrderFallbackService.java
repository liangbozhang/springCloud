package com.test.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class OrderFallbackService implements OrderService{

    //服务降级
    @Override
    public String paymentInfoOk(Long id) {
        return "********* paymentInfoOk ******网络繁忙，请稍后再试！";
    }

    @Override
    public String paymentInfoTimeOut(Long id) {
        return "********* paymentInfoTimeOut ******网络繁忙，请稍后再试！";
    }

    @Override
    public String paymentInfoTimeOut1(Long id) {
        return "--------- paymentInfoTimeOut1 *******网络繁忙，请稍后再试！";
    }

}
