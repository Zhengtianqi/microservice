package com.itmuch.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
/*
* zuul的简单用法
* eureka    abc:7900    /simple/1
* zuul可以访问localhost或主机名
* 可以通过localhost:ZUUL_PORT/abc/simple/1
* */
@SpringBootApplication
@EnableZuulProxy
// @EnableDiscoveryClient
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
