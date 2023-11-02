package com.test.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.test.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl  implements PaymentService {

    //正常访问
    @Override
    public String paymentInfoOk(Long id) {
        return "线程池： " + Thread.currentThread().getName() + " paymentInfoOk， id： " + id + "\t" + " O(∩_∩)O哈哈~";
    }

    //正常访问
    @Override
    public String paymentInfoTimeOut(Long id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + " 运行正常， id： " + id + "\t" + " O(∩_∩)O哈哈~";
    }


    //服务降级 超时异常/运行异常
    @Override
    @HystrixCommand(fallbackMethod = "fallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentHystrixDowngrading(Long id) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + " 超时运行正常， id： " + id + "\t" + " O(∩_∩)O哈哈~";
    }

    public String fallbackMethod(Long id){

        return "线程池： " + Thread.currentThread().getName() + " 系统繁忙，请稍后重试。 id： " + id + "\t" + " o(╥﹏╥)o";
    }


    //服务熔断
    @Override
    @HystrixCommand(fallbackMethod = "fallbackMethodFusing", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //开启服务熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") //失败率
    })
    public String paymentHystrixFusing(Long id) {
        if (id < 0){
            throw new RuntimeException("************ id 不能为负");
        }
        String serialNumber = IdUtil.simpleUUID();

        return "线程池： " + Thread.currentThread().getName() + "\t" + " 调用成功， 流水号： "  + serialNumber;
    }

    public String fallbackMethodFusing(Long id){

        return " id 不能为负数，请稍后重试。 id： " + id + " o(╥﹏╥)o";
    }

}
