package com.test.springcloud.controller;

import com.test.springcloud.entity.PaymentEntity;
import com.test.springcloud.service.PaymentService;
import com.test.springcloud.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @PostMapping("/save")
    public CommonResult<Boolean> save(@RequestBody PaymentEntity paymentEntity){
        CommonResult<Boolean> result = new CommonResult<>();
        result.setExt(port);
        if (paymentEntity.getSerial() == null || "".equals(paymentEntity.getSerial())){
            return result.error("订单金额不能为空！", false);
        }
        Integer insert = paymentService.insert(paymentEntity);
        log.info("****机器端口：{}，插入结果：{}", port, insert);
        if (insert > 0){
            return result.ok("保存数据成功。", true);
        }else {
            return result.error("保存数据失败！", false);
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult<PaymentEntity> get(@PathVariable("id") Long id){
        CommonResult<PaymentEntity> result = new CommonResult<>();
        result.setExt(port);
        PaymentEntity paymentEntity = paymentService.get(id);
        log.info("****机器端口：{}，8002查询结果：{}", port, paymentEntity);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result.ok(paymentEntity);
    }

    @GetMapping("/port")
    public String getPort(){
        return port;
    }


}
