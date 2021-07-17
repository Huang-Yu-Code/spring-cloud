package com.dem.openfeign.controller;

import com.dem.openfeign.feignclient.EurekaFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author codingob
 */
@RestController
public class FeignController {

    @Value("${spring.application.name}")
    private String service;

    @Value("${server.port}")
    private String port;

    @Resource
    private EurekaFeignClient eurekaFeignClient;

    @GetMapping("/")
    public String index() {
        return service + "---" + port;
    }

    @GetMapping("/provider")
    public String getProvider() {
        return eurekaFeignClient.index();
    }
}
