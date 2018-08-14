package com.itmuch.cloud.controller;

import com.itmuch.cloud.entity.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
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
    private RestTemplate restTemplate;
    //Eureka客户端
    @Autowired
    private EurekaClient eurekaClient;
    /**
     * 获得Eureka instance的信息，传入Application NAME
     *
     * */
    @GetMapping("/movie/eureka-instance")
    public String movieServiceUrl(){
        InstanceInfo instance = this.eurekaClient.getNextServerFromEureka("microservice-consumer-movie", false);
        return instance.getHomePageUrl();
    }
    @GetMapping("/movie/{id}")
    public User findById(@PathVariable Long id){
        return this.restTemplate.getForObject("http://localhost:7900/simple/" + id, User.class);
    }
}
