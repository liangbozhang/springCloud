package com.test.springcloud.service;

import com.test.springcloud.entity.PaymentEntity;

public interface PaymentService {

    Integer insert(PaymentEntity paymentEntity);

    PaymentEntity get(Long id);

}
