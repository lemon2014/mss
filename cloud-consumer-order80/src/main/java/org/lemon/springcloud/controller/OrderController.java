package org.lemon.springcloud.controller;

import org.lemon.springcloud.entity.CommonResult;
import org.lemon.springcloud.entity.Payment;
import org.lemon.springcloud.lb.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class OrderController {

    //  public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/consumet/payment/create")
    public CommonResult create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping(value = "/consumet/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    /**
     * getForEntity相对于getForObject来说, 可以获取状态码, 头部等详细信息
     */
    @GetMapping(value = "/consumet/payment/getEntity/{id}")
    public CommonResult<Payment> getEntityById(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(entity.getStatusCodeValue(), "接口调用失败！！！！！");
        }
    }

    @GetMapping(value = "/consumet/payment/lb/{id}")
    public CommonResult<Payment> getPaymentByLB(@PathVariable("id") Long id) {
        // 获取支付服务的实例列表，这里使用的是硬编码的方式，
        List<ServiceInstance> list = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance serviceInstance = loadBalancer.instances(list);
        return restTemplate.getForObject(serviceInstance.getUri() + "/payment/get/" + id, CommonResult.class);
    }

}
