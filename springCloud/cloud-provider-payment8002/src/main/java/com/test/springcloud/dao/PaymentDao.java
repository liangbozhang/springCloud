package com.test.springcloud.dao;

import com.test.springcloud.entity.PaymentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int insert(PaymentEntity paymentEntity);

    PaymentEntity getPaymentById(@Param("id") Long id);
}
