package com.itmuch.cloud.controller;

import com.itmuch.cloud.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.SessionScope;

@RestController
@SessionScope  // 和@Scope("session")
public class MovieController {
    /**
     * @Autowired 报错,在main方法实例化一个
     */
    @Autowired
    RestTemplate restTemplate; // 定义为私有可能会报错

    @GetMapping("/movie/{id}")
    @HystrixCommand(fallbackMethod = "findByIdFallback", commandProperties = @HystrixProperty(name="execution.isolation.strategy", value = "SEMAPHORE"))
    public User findById(@PathVariable Long id){
        // http://localhost:7900/simple
        // VIP virtual IP 请求服务提供者的service id
        // HAProxy Heartbeat
        return this.restTemplate.getForObject("http://microservice-provide-user/simple/" + id, User.class);
    }

    /**
     * 服务器挂掉了之后  默认不请求 直接返回
     * @param id
     * @return
     */
    public User findByIdFallback(Long id){
        User user = new User();
        user.setId(0L);
        return user;
    }
}
