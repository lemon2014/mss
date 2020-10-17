package org.lemon.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义负载均衡算法（轮询）
 */
@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    final int currentIndex() {
        int current;
        int next;
        for (; ; ) {
            current = atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
            if (this.atomicInteger.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = currentIndex() % serviceInstances.size();
        System.out.println("获取实例列表的索引值:" + index);
        return serviceInstances.get(index);
    }
}
