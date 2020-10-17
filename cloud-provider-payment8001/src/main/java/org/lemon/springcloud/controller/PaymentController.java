package org.lemon.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.lemon.springcloud.service.PaymentService;
import org.lemon.springcloud.entity.CommonResult;
import org.lemon.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String servicePort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入结果：" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据库成功serverPort:" + servicePort, result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort:" + servicePort, payment);
        } else {
            return new CommonResult(444, "没有对应记录,查询ID: " + id, null);
        }
    }

    /**
     * 客户端获取注册中心的服务列表信息
     * @return
     */
    @GetMapping(value = "/payment/serverinfo")
    public Object getEurekaInfo() {
        List<String> services = discoveryClient.getServices();
        services.forEach(System.out::println);

        List<ServiceInstance> list = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        list.forEach(t -> {
            log.info(t.getInstanceId() + "\t" + t.getHost() + "\t" + t.getPort() + "\t" + t.getUri());
        });
        return this.discoveryClient;
    }

    /**
     * 模拟业务处理逻辑时间比较长，feign调用超时问题处理
     *
     */
    @GetMapping(value = "/payment/timeout")
    public String getTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return servicePort;
    }
}
