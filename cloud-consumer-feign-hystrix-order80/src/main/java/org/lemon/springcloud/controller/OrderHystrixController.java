package org.lemon.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.lemon.springcloud.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@DefaultProperties(defaultFallback = "paymentGlobalFallBackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {

        return "fail" + (8/0);
//        return paymentHystrixService.paymentInfo_OK(id);
    }

    /**
     * 客户端自己定义等待时间,超过1.5秒,没有结果返回就执行fallback方法
     *
     */
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeoutFallBackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
//        return paymentHystrixService.paymentInfo_TimeOut(id);
//        int temp = 8/0;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

    public String paymentTimeoutFallBackMethod(Integer id) {
        return "服务端繁忙,或者本系统异常";
    }

    public String paymentGlobalFallBackMethod() {
        return "全局的fallback,服务端繁忙,或者本系统异常";
    }



}
