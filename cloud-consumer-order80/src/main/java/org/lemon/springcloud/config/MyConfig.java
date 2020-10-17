package org.lemon.springcloud.config;

import org.lemon.springcloud.autowired.AImpl;
import org.lemon.springcloud.autowired.MyInterface;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    @Bean
    @LoadBalanced  //开启负载均衡
    public RestTemplate getRestTemplate(AImpl myInterface) {

        System.out.println("自动装配：" + myInterface);

        return new RestTemplate();
    }

}
