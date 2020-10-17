package org.lemon.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixServiceImpl implements PaymentHystrixService{


    @Override
    public String paymentInfo_OK(Integer id) {
        return "全局fallback处理";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "全局fallback处理";
    }
}
