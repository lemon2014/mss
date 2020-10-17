package org.lemon.springcloud.service;

import org.apache.ibatis.annotations.Param;
import org.lemon.springcloud.entity.Payment;

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
