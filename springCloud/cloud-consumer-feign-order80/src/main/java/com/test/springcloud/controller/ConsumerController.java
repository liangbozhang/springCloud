package com.test.springcloud.controller;

import com.test.springcloud.entity.PaymentEntity;
import com.test.springcloud.service.ConsumerFeignServer;
import com.test.springcloud.utils.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Resource
    private ConsumerFeignServer consumerFeignServer;

    @GetMapping("get/{id}")
    public CommonResult<PaymentEntity> get(@PathVariable("id") Long id){

        return consumerFeignServer.getPaymentById(id);

    }
}
