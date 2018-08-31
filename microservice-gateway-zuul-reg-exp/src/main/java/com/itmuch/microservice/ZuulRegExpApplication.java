package com.itmuch.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/*
* zuul的简单用法
* eureka    abc:7900    /simple/1
* zuul可以访问localhost或主机名
* 可以通过localhost:ZUUL_PORT/abc/simple/1
* */
@SpringBootApplication
@EnableZuulProxy
// @EnableDiscoveryClient
public class ZuulRegExpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulRegExpApplication.class, args);
    }
    /*
    *  使用正则表达式指定路由规则
    * */
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper(
                "(?<name>^.+)-(?<version>v.+$)",
                "${version}/${name}");
    }
}
