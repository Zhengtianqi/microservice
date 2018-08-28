package com.itmuch.cloud.controller;

import com.itmuch.cloud.feign.FeignClient2;
import com.itmuch.cloud.feign.UserFeignClient;
import com.itmuch.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private FeignClient2 feignClient2;

    @GetMapping("/movie/{id}") // 可能会超时
    public User findById(@PathVariable Long id){
        return userFeignClient.findById(id);
    }

    @GetMapping("/{serviceName}")
    public String findServiceInfoFromEurekaByServiceName(@PathVariable String serviceName){
        return this.feignClient2.findEurekaByServiceName(serviceName);
    }
}
