package com.itmuch.cloud.controller;

import com.itmuch.cloud.Repository.UserRepository;
import com.itmuch.cloud.entity.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EurekaClient eurekaClient;

    /*@Autowired
    private DiscoveryClient discoveryClient;*/
    @GetMapping("/simple/{id}")  // 点进去可以看到||相当于@RequestMapping(value = "/xxx" method = GET)
    public User findById(@PathVariable Long id) {
        User user = this.userRepository.getOne(id);
        return user;
    }
    /*
     * 发现当前机器的ip和端口号
     */
    @GetMapping("/eureka-instance")
    public String serviceUrl() {
        InstanceInfo instance = this.eurekaClient.getNextServerFromEureka("microservice-provide-user", false);
        return instance.getHomePageUrl();
    }
    @GetMapping("/hello")
    public String hello(){
        return "helloworld";
    }
/*  获取当前机器的详细信息
    @GetMapping("/instance-info")
    public ServiceInstance showInfo(){
        ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
        return localServiceInstance;
    }*/

    /**
     * post
     * @param user
     * @return
     */
    @PostMapping("/user")
    public User postUser(@RequestBody User user){
        return user;
    }
}
