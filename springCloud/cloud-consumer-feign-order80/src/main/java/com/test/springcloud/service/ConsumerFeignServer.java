package com.test.springcloud.service;

import com.test.springcloud.entity.PaymentEntity;
import com.test.springcloud.utils.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
@RequestMapping("/cloud-payment-service")
public interface ConsumerFeignServer {

    @GetMapping("/payment/get/{id}")
    CommonResult<PaymentEntity> getPaymentById(@PathVariable("id") Long id);
}
