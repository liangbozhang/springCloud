package com.test.springcloud.controller;

import com.test.springcloud.entity.PaymentEntity;
import com.test.springcloud.lb.LoadBalancer;
import com.test.springcloud.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("consumer")
@Slf4j
public class OrderController {

    //public static final String PAYMENT_URL = "http://localhost:8001/cloud-payment-service";   //单机配置
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE/cloud-payment-service"; //集群配置

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("create")
    public CommonResult save(PaymentEntity paymentEntity){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/save", paymentEntity, CommonResult.class);
    }

    @GetMapping("get/{id}")
    public CommonResult get(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    //相对于 getForObject 来说 getForEntity 返回的信息更加详细全面， 可以认为：object返回一个json，entity返回一个ResponseEntity对象
    @GetMapping("create/entity")
    public CommonResult saveEntity(PaymentEntity paymentEntity){
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/save", paymentEntity, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult().error("操作失败！");
        }
    }

    @GetMapping("get/entity/{id}")
    public CommonResult getForEntity(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        log.info(forEntity.toString());
        if (forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else {
            return new CommonResult().error("操作失败！");
        }
    }

    @GetMapping("/port")
    public String getPort(){

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if (instances == null || instances.size() <= 0){
            return null;
        }

        ServiceInstance instance = loadBalancer.instance(instances);
        URI uri = instance.getUri();

        return restTemplate.getForObject(uri + "/cloud-payment-service/payment/port", String.class);

    }

}
