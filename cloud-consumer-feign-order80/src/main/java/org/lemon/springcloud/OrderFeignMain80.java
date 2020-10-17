package org.lemon.springcloud;

import org.lemon.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }


    @Bean
    public CommandLineRunner run(BeanFactory beanFactory){
        return args -> {
            System.out.println("====================");
            System.out.println(args);

            //org.lemon.springcloud.service.PaymentFeignService
            PaymentFeignService feignService = (PaymentFeignService)beanFactory.getBean("org.lemon.springcloud.service.PaymentFeignService");
            System.out.println(feignService);
        };
    }

}
