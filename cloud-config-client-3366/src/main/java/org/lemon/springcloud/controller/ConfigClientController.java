package org.lemon.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${username}")
    private String username;

    /**
     * 数据库配置修改后, 配置中心服务端可以获取到最新的文件, 但是客户端这边不会更新到最新的配置, 需要重启
     *
     * 如何不重启
     * 1、bootstrap.yml文件开启监控端点
     * 2、添加@RefreshScope注解
     * 3、执行curl -X POST "http://localhost:3355/actuator/refresh"
     *
     * 执行上面三个步骤才能生效，还是有问题
     *
     * 1、如果有一百台服务，需要发送一百个curl请求
     * 2、@RefreshScope加到什么地方合适？？
     *
     */
    @GetMapping("/username")
    public String username() {
        return username;
    }
}
