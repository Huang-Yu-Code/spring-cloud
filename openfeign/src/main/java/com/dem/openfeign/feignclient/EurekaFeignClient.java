package com.dem.openfeign.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yu
 */
@Component
@FeignClient("eureka-provider")
public interface EurekaFeignClient {

    /**
     * return String
     */
    @GetMapping("/")
    String index();
}
