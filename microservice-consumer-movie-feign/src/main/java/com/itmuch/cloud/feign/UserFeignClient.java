package com.itmuch.cloud.feign;

import com.itmuch.cloud.entity.User;
// import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("microservice-provide-user")
public interface UserFeignClient {
    @RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);// 两个坑 ， 1. @GetMapping不支持 2. @PathVariable需要设置参数 3. 包名改了
}
