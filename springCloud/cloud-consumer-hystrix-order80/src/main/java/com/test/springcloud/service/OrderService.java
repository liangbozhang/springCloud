package com.test.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
//配置全局异常处理类 OrderFallbackService
@FeignClient(value = "CLOUD-HYSTRIX-PAYMENT-SERVER", fallback = OrderFallbackService.class)
public interface OrderService {

    //正常返回
    @GetMapping("/cloud-hystrix-payment-server/payment/info/ok/{id}")
    String paymentInfoOk(@PathVariable("id") Long id);

    //延迟一秒返回
    @GetMapping("/cloud-hystrix-payment-server/payment/info/time/out/{id}")
    String paymentInfoTimeOut(@PathVariable("id") Long id);

    //服务降级
    @GetMapping("/cloud-hystrix-payment-server/payment/hystrix/downgrading/{id}")
    String paymentInfoTimeOut1(@PathVariable("id") Long id);

}
