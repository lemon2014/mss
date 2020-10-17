package org.lemon.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
//@EnableHystrix
@EnableCircuitBreaker  //这个和@EnableHystrix有什么区别？为什么服务端用的是前者，客户端用的后者
public class HystrixOrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(HystrixOrderMain80.class, args);
    }
}
