package org.lemon.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.lemon.springcloud.entity.CommonResult;
import org.lemon.springcloud.entity.Payment;
import org.lemon.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/timeout")
    public String getTimeout(){

        // feign默认的等待时间是1秒, 操作等待时间就会报错
        return paymentFeignService.getTimeout();
    }
}
