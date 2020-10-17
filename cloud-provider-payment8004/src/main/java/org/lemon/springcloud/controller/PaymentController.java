package org.lemon.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.lemon.springcloud.service.PaymentService;
import org.lemon.springcloud.entity.CommonResult;
import org.lemon.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String servicePort;

    @RequestMapping(value = "/payment/zk")
    public String paymentZK(){
        return "springcloud with zookeeper:" + servicePort;
    }
}
