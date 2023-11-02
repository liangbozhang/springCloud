package com.test.springcloud.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.test.springcloud.dao.PaymentDao;
import com.test.springcloud.entity.PaymentEntity;
import com.test.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;


    @Override
    public Integer insert(PaymentEntity paymentEntity) {
        paymentEntity.setId(new Snowflake().nextId());
        return paymentDao.insert(paymentEntity);
    }

    @Override
    public PaymentEntity get(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
