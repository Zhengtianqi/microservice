package com.itmuch.cloud.controller;

import com.itmuch.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {
    /**
     * @Autowired 报错,在main方法实例化一个
     */
    @Autowired
    RestTemplate restTemplate; // 定义为私有可能会报错

    @GetMapping("/movie/{id}")
    public User findById(@PathVariable Long id){
        // http://localhost:7900/simple
        // VIP virtual IP 请求服务提供者的service id
        // HAProxy Heartbeat
        return this.restTemplate.getForObject("http://microservice-provide-user/simple/" + id, User.class);
    }
}
