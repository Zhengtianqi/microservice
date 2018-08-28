package com.itmuch.cloud.feign;

import com.itmuch.config.Configuration2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "xxxx", url = "http://localhost:8761/", configuration = Configuration2.class, fallback = HystrixClientFallback.class)
public interface FeignClient2 {
    @RequestMapping("/eureka/apps/{serviceName}")
    public String findEurekaByServiceName(@PathVariable("serviceName") String serviceName);
}
