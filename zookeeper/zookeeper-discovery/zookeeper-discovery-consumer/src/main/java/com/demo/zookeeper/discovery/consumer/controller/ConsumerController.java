package com.demo.zookeeper.discovery.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ConsumerController {

    @Value("${spring.application.name}")
    private String service;

    @Value("${server.port}")
    private int port;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/")
    public String index() {
        return service + "---" + port;
    }

    @GetMapping("/provider")
    public String getProvider() {
        List<ServiceInstance> instances = discoveryClient.getInstances("zookeeper-discovery-provider");
        if (instances != null && instances.size() > 0) {
            String url = instances.get(0).getUri().toString();
            System.out.println(url);
            return restTemplate.getForObject(url, String.class);
        }
        return restTemplate.getForObject("http://localhost:/8081", String.class);
    }
}
