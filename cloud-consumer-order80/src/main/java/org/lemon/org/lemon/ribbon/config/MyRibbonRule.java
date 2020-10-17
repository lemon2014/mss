package org.lemon.org.lemon.ribbon.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 不能定义在启动类能够扫描到的地方，否则它会覆盖默认的配置类，也就是调用所有的服务都会使用下面的规则
 *
 * 这种方式可以针对某个服务使用该规则，详见启动类上面的注解
 *
 */
@Configuration
public class MyRibbonRule {

    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}
