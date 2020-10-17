package org.lemon.springcloud.config;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fixme  还不清楚这个具体有什么用
 */
@Configuration
public class ZuuPatternServiceRouteMapperConfiguration {
    //注意！！！这两者保留一个即可。

    /**
     * 没有版本号的路由匹配规则bean
     * @retu没rn 路由匹配规则bean
     */
    @Bean
    public PatternServiceRouteMapper patternServiceRouteMapper(){

        // 下面的配置没有实际意义，
        return new PatternServiceRouteMapper("(?<name>.*$)","${name}");
    }

    /**
     * 有版本号的路由匹配规则bean
     * @return 路由匹配规则bean
     */
//    @Bean
//    public PatternServiceRouteMapper patternServiceRouteMapperWithVersion(){
//        return new PatternServiceRouteMapper("(?<name>.*)-(?<version>v.*$)","${version}/${name}");
//    }

}
