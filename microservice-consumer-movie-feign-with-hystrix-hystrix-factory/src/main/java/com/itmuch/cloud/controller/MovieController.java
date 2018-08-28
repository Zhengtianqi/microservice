package com.itmuch.cloud.controller;

import com.itmuch.cloud.feign.UserFeignClient;
import com.itmuch.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {
    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/movie/{id}") // 可能会超时
    public User findById(@PathVariable Long id){
        return this.userFeignClient.findById(id);
    }
}
