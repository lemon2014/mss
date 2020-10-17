package org.lemon.springcloud.service.impl;

import org.lemon.springcloud.dao.PaymentDao;
import org.lemon.springcloud.service.PaymentService;
import org.lemon.springcloud.entity.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
